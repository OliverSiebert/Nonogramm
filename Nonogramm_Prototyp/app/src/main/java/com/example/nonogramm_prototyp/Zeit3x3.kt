package com.example.nonogramm_prototyp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class Zeit3x3 : AppCompatActivity() {
    private val datenbank = DatenbankKlasse(this)
    private val shopDB = ShopDatenbank(this)
    private var currentSymbol = "FILL"
    private var solutionText = arrayOf("", "", "", "", "", "", "", "", "")
    private var solutionInt = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var sumArray = arrayOf(0, 0, 0, 0, 0, 0)
    private var gameState = "playing"
    private var geldValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generateNewGame()
        geldValue = lesen()

        farbeAendern(level)
        farbeAendern(statusText)
        val leser = shopDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM color WHERE selected = 1", null)
        ergebnis.moveToNext()
        var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
        var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
        leser.close()
        //ganzeSeite.setTextColor(Color.parseColor(fontColor.toString()))
        ganzeSeite.setBackgroundColor(Color.parseColor(backgroundColor))

        //Test Level

        /*val pref = getPreferences(Context.MODE_PRIVATE)
        val level_nr = pref.getInt("LEVEL",1)
        level.setText(level_nr.toString())*/

        //

        // Fill und X Wechsel
        symbolChanger.text = "FILL"
        symbolChanger.setOnClickListener(){
            if(symbolChanger.text == "FILL"){
                symbolChanger.text = "X"
                currentSymbol = "X"
            }
            else{
                symbolChanger.text = "FILL"
                currentSymbol = "FILL"
            }
        }

        //Definition Felder
        val allFields = arrayOf(f0, f1, f2, f3, f4, f5, f6, f7, f8)
        for(field in allFields){
            field.setOnClickListener(){
                onFieldClick(it as TextView)
            }
        }

        //Reset Button
        reset.setOnClickListener(){
            resetGame()
        }

        //New Game Button
        newGame.setOnClickListener(){
            resetGame()
            generateNewGame()
        }
    }

    // Fuellen der Felder durch Klicken
    @SuppressLint("ResourceType")
    private fun onFieldClick(field: TextView){
        if ((field.text == "FILL") || (field.text == "X")){
            field.setTextColor(Color.parseColor("#FFFFFF"))
            field.text = ""
            field.setBackgroundColor(Color.WHITE)
        }
        else if(currentSymbol == "X"){
            field.setTextColor(Color.parseColor("#F44336"))
            field.text = currentSymbol
        }
        else if(currentSymbol == "FILL"){
            field.setBackgroundColor(Color.GRAY)
            field.setTextColor(Color.GRAY)
            field.text = currentSymbol

        }
        gameState = "playing"




        if(checkWin()){
            Toast.makeText(this@Zeit3x3, getString(R.string.win), Toast.LENGTH_SHORT).show()
            aendern()
            gameState="won"
            //saveLevel()
            resetGame()
            generateNewGame()
        }



    }
    private fun farbeAendern(field: TextView) {
        val leser = shopDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM color WHERE selected = 1", null)
        ergebnis.moveToNext()
        var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
        var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
        leser.close()
        field.setTextColor(Color.parseColor(fontColor))
        field.setBackgroundColor(Color.parseColor(backgroundColor))
    }

    private fun aendern() {
       val schreiber = datenbank.writableDatabase
        val datensatz = ContentValues()
        geldValue += 1
        datensatz.put("geld", geldValue)
        schreiber.update(
            "spieler", datensatz, "name='Max'", null)
        level.text = "%d $" .format(geldValue) //geldValue.toString()
        schreiber.close()
    }

    // Gewinn Ueberpruefung
    private fun checkWin(): Boolean {
        if(pruefe(f0 as TextView, f1 as TextView, f2 as TextView,0, f012 as TextView)){f012.setBackgroundColor(Color.GREEN)}else{f012.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f3 as TextView, f4 as TextView, f5 as TextView,1, f345 as TextView)){f345.setBackgroundColor(Color.GREEN)}else{f345.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f6 as TextView, f7 as TextView, f8 as TextView,2, f678 as TextView)){f678.setBackgroundColor(Color.GREEN)}else{f678.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f0 as TextView, f3 as TextView, f6 as TextView,3, f036 as TextView)){f036.setBackgroundColor(Color.GREEN)}else{f036.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f1 as TextView, f4 as TextView, f7 as TextView,4, f147 as TextView)){f147.setBackgroundColor(Color.GREEN)}else{f147.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f2 as TextView, f5 as TextView, f8 as TextView,5, f258 as TextView)){f258.setBackgroundColor(Color.GREEN)}else{f258.setBackgroundColor(Color.parseColor("#00ddff"))}

        //return  ((f0.text==solutionText[0]) && (f1.text==solutionText[1])  && (f2.text==solutionText[2] ) && (f3.text==solutionText[3])  && (f4.text==solutionText[4]) && (f5.text==solutionText[5]) && (f6.text==solutionText[6] )&& (f7.text==solutionText[7]) && (f8.text==solutionText[8]))
        return pruefe(f0 as TextView, f1 as TextView, f2 as TextView,0, f012 as TextView) && pruefe(f3 as TextView, f4 as TextView, f5 as TextView,1, f345 as TextView) && pruefe(f6 as TextView, f7 as TextView, f8 as TextView,2, f678 as TextView) && pruefe(f0 as TextView, f3 as TextView, f6 as TextView,3, f036 as TextView) && pruefe(f1 as TextView, f4 as TextView, f7 as TextView,4, f147 as TextView) && pruefe(f2 as TextView, f5 as TextView, f8 as TextView,5, f258 as TextView)
    }

    //Reset Funktion
    private fun resetGame(){
        val allFields = arrayOf(f0, f1, f2, f3, f4, f5, f6, f7, f8)
        for(field in allFields){
            field.text=""
            field.setBackgroundColor(Color.WHITE)
        }
        var aussenFelder = arrayOf(f012, f036, f147, f258, f345, f678)
        for(field in aussenFelder){
            field.setBackgroundColor(Color.parseColor("#00ddff"))
        }
        gameState = "restarting"

    }

    //Generate new Puzzle-Game
    private fun generateNewGame(){
        var i=0
        while(i<9){
            solutionInt[i] = Random.nextInt(2)
            solutionText[i] = if(solutionInt[i] == 1 ) "FILL" else ""
            i += 1
        }
        //Beschriftung der Achsen
        sumArray[0]= beschriftenZellen(0,1,2, f012 as TextView, false)
        sumArray[1]= beschriftenZellen(3,4,5, f345 as TextView, false)
        sumArray[2]= beschriftenZellen(6,7,8, f678 as TextView, false)

        sumArray[3]= beschriftenZellen(0,3,6, f036 as TextView, true)
        sumArray[4]= beschriftenZellen(1,4,7, f147 as TextView, true)
        sumArray[5]= beschriftenZellen(2,5,8, f258 as TextView, true)

        var x = 0
        for(sum in sumArray){
            x += sum
        }
        if(x == 0){
            generateNewGame()
        }
        gameState = "generating"

    }

    //Beschriften der Zellen
    private fun beschriftenZellen(a:Int, b: Int, c:Int, d:TextView, hoch:Boolean): Int{
        var sum = solutionInt[a]+solutionInt[b]+solutionInt[c]
        if(sum != 2){
            d.text = sum.toString()
        }
        else{
            if(solutionInt[b] == 1){d.text ="2"}
            else {
                d.text= if(hoch) "1\n1" else "1 1"
            }
        }
        return sum
    }

    //Auf Vollstaendigkeit pruefen
    private fun pruefe(fa: TextView, fb: TextView, fc: TextView, current: Int, fabc: TextView): Boolean{
        var tmp = 0
        if(fa.text=="FILL"){
            tmp += 1
        }
        if(fb.text=="FILL"){
            tmp += 1
        }
        if(fc.text=="FILL"){
            tmp += 1
        }
        //weitere if Bediengung um 1,1 und 2 zu unterscheiden, ist noch nicht vorhanden
        if(tmp == 2){
            if((fa.text == fc.text) && (fabc.text == "1\n1")){
                return true
            }
            else if((fa.text == fc.text) && (fabc.text == "1 1")){
                return true
            }
            else if((fa.text != fc.text) &&(fb.text == "FILL") && (fabc.text == "2")) {
                return true
            }
            return false
        }
        return sumArray[current] == tmp
    }

    //Level sichern
    private fun saveLevel(){
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()
        var a = level.text.toString().toInt() + 1
        level.text = a.toString()
        editor.putInt("LEVEL", a)
        editor.commit()
    }
    private fun lesen(): Int {
        val leser = datenbank.readableDatabase
        var daten = ""
        val ergebnis = leser.rawQuery(
            "SELECT * FROM spieler", null)
        ergebnis.moveToNext()
        val geldInt = ergebnis.getInt(ergebnis.getColumnIndex("geld"))
        daten += "%d $" .format(geldInt)
        level.text = daten
        leser.close()
        return geldInt

    }
}
