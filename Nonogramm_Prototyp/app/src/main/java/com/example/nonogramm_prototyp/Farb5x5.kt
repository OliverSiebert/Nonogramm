package com.example.nonogramm_prototyp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.farb5x5.*
import kotlin.random.Random


class Farb5x5: AppCompatActivity() {
    private val datenbank = DatenbankKlasse(this)
    private var currentSymbol = "1"
    private var solution= arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)
    private var geldValue = 0
    private val shopDB = ShopDatenbank(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.farb5x5)
        geldValue = lesen()
        symbolA.setBackgroundResource(R.drawable.active_red)

        val allFields = arrayOf(fA1, fB1, fC1, fD1, fE1, fA2, fB2, fC2, fD2, fE2, fA3, fB3, fC3, fD3, fE3, fA4, fB4, fC4, fD4, fE4, fA5, fB5, fC5, fD5, fE5)
            for(field in allFields){
            field.setOnClickListener(){
                onFieldClick(it as TextView)
            }
        }
        generateNewGame()

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

        //val pref = getPreferences(Context.MODE_PRIVATE)
        //val level_nr = pref.getInt("MONEY",0)
        //level.setText(level_nr.toString())

        //Buttons zum Farbe aendern
        symbolA.text = "R"
        symbolA.setOnClickListener(){
            currentSymbol = "1"
            symbolA.setBackgroundResource(R.drawable.active_red)
            symbolB.setBackgroundColor(Color.parseColor("#FF9800"))
            symbolC.setBackgroundColor(Color.parseColor("#FFEB3B"))
            symbolX.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        symbolB.text = "O"
        symbolB.setOnClickListener() {
            currentSymbol = "2"
            symbolA.setBackgroundColor(Color.parseColor("#F44336"))
            symbolB.setBackgroundResource(R.drawable.active_orange)
            symbolC.setBackgroundColor(Color.parseColor("#FFEB3B"))
            symbolX.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        symbolC.text = "Y"
        symbolC.setOnClickListener(){
                currentSymbol = "3"
            symbolA.setBackgroundColor(Color.parseColor("#F44336"))
            symbolB.setBackgroundColor(Color.parseColor("#FF9800"))
            symbolC.setBackgroundResource(R.drawable.active_yellow)
            symbolX.setBackgroundColor(Color.parseColor("#FFFFFF"))

        }
        symbolX.text = "X"
        symbolX.setOnClickListener(){
            currentSymbol = "X"
            symbolA.setBackgroundColor(Color.parseColor("#F44336"))
            symbolB.setBackgroundColor(Color.parseColor("#FF9800"))
            symbolC.setBackgroundColor(Color.parseColor("#FFEB3B"))
            symbolX.setBackgroundResource(R.drawable.active_x)
        }
        reset.setOnClickListener(){
            resetGame()
        }
        //New Game Button
        newGame.setOnClickListener(){
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

    private fun generateNewGame() {
        var i = 0
        while (i < 25) {
            solution[i] = Random.nextInt(4)
            i++
        }
        beschrifte(fA as TextView, 0, 5, 10, 15, 20, true)
        beschrifte(fB as TextView, 1, 6, 11, 16, 21, true)
        beschrifte(fC as TextView, 2, 7, 12, 17, 22, true)
        beschrifte(fD as TextView, 3, 8, 13, 18, 23, true)
        beschrifte(fE as TextView, 4, 9, 14, 19, 24, true)

        beschrifte(f1 as TextView, 0, 1, 2, 3, 4, false)
        beschrifte(f2 as TextView, 5, 6, 7, 8, 9, false)
        beschrifte(f3 as TextView, 10, 11, 12, 13, 14, false)
        beschrifte(f4 as TextView, 15, 16, 17, 18, 19, false)
        beschrifte(f5 as TextView, 20, 21, 22, 23, 24, false)


    }

    private fun beschrifte(summe: TextView, zahl1: Int, zahl2: Int, zahl3: Int, zahl4: Int, zahl5: Int, hoch: Boolean) {
        var zahlen = arrayOf(solution[zahl1], solution[zahl2], solution[zahl3], solution[zahl4], solution[zahl5], 0)
        var counterA=0
        var zahlA=0
        var counterB=0
        var zahlB=0
        var counterC=0
        var zahlC=0
        var ausgabe = arrayOf("0")
        for(zahl in zahlen){
            if((zahlB == 0) && (zahlC == 0)){
                if(zahl == 1){
                    zahlA +=1
                }
            }
            if((zahlA == 0) && (zahlC == 0)){
                if(zahl == 2){
                    zahlB +=1
                }
            }
            if((zahlA == 0) && (zahlB == 0)){
                if(zahl == 3){
                    zahlC +=1
                }
            }
            if((counterA==zahlA)&&(counterB==zahlB)&&(counterC==zahlC)){
                if((zahlC != 0)){ausgabe = ausgabe.plus("Y:"+zahlC.toString())}
                if((zahlA != 0)){ausgabe = ausgabe.plus("R:"+zahlA.toString())}
                if((zahlB != 0)){ausgabe = ausgabe.plus("O:"+zahlB.toString())}
                zahlA=0
                zahlB=0
                zahlC=0
                if(zahl == 1){
                    zahlA = 1
                }
                if(zahl == 2){
                    zahlB = 1
                 }
                if(zahl == 3) {
                    zahlC = 1
                }
            }
            counterA=zahlA
            counterB=zahlB
            counterC=zahlC
        }
        var max = ausgabe.size-1
        if(ausgabe.size != 1){ausgabe = ausgabe.sliceArray(1..max)}
        if(hoch){summe.text = ausgabe.joinToString("\n")}
        else{summe.text = ausgabe.joinToString(" ")}

    }

    private fun pruefe(summe: TextView, feld1: TextView, feld2: TextView, feld3: TextView, feld4: TextView, feld5: TextView, hoch: Boolean): Boolean { //: Array<Int>
        var text = arrayOf(feld1.text, feld2.text, feld3.text, feld4.text, feld5.text, 0)
        var tmptext: String
        var counterA=0
        var zahlA=0
        var counterB=0
        var zahlB=0
        var counterC=0
        var zahlC=0
        var ausgabe = arrayOf("0")
        for(zahl in text){
            if((zahlB == 0) && (zahlC == 0)){
                if(zahl == "1"){
                    zahlA +=1
                }
            }
            if((zahlA == 0) && (zahlC == 0)){
                if(zahl == "2"){
                    zahlB +=1
                }
            }
            if((zahlA == 0) && (zahlB == 0)){
                if(zahl == "3"){
                    zahlC +=1
                }
            }
            if((counterA==zahlA)&&(counterB==zahlB)&&(counterC==zahlC)){
                if((zahlC != 0)){ausgabe = ausgabe.plus("Y:"+zahlC.toString())}
                if((zahlA != 0)){ausgabe = ausgabe.plus("R:"+zahlA.toString())}
                if((zahlB != 0)){ausgabe = ausgabe.plus("O:"+zahlB.toString())}
                zahlA=0
                zahlB=0
                zahlC=0
                if(zahl == "1"){
                    zahlA = 1
                }
                if(zahl == "2"){
                    zahlB = 1
                }
                if(zahl == "3") {
                    zahlC = 1
                }
            }
            counterA=zahlA
            counterB=zahlB
            counterC=zahlC
        }
        var max = ausgabe.size-1
        if(ausgabe.size != 1){ausgabe = ausgabe.sliceArray(1..max)}
        if(hoch){tmptext = ausgabe.joinToString("\n")}
        else{tmptext = ausgabe.joinToString(" ")}
        return tmptext == summe.text
    }

    @SuppressLint("ResourceType")
    private fun onFieldClick(field: TextView) {
        if ((field.text == "1") || (field.text == "2")|| (field.text == "3")|| (field.text == "X")){
            field.setTextColor(Color.parseColor("#FFFFFF"))
            field.text = ""
            field.setBackgroundColor(Color.WHITE)
        }
        else if(currentSymbol == "X"){
            field.text = currentSymbol
            //field.textSize=16F
            field.setTextColor(Color.parseColor("#F44336"))
            //field.setBackgroundResource(17301533)
        }

        else if(currentSymbol == "1"){
            field.setBackgroundColor(Color.RED)
            field.setTextColor(Color.RED)
            field.text = currentSymbol
        }
        else if(currentSymbol == "2"){
            field.setBackgroundColor(Color.parseColor("#FF9800"))
            field.setTextColor(Color.parseColor("#FF9800"))
            field.text = currentSymbol
        }
        else if(currentSymbol == "3"){
            field.setBackgroundColor(Color.YELLOW)
            field.setTextColor(Color.YELLOW)
            field.text = currentSymbol
        }
        if(checkWin()){
            Toast.makeText(this@Farb5x5, getString(R.string.win), Toast.LENGTH_SHORT).show()
            aendern()
            //winMoney()
            //level.text=geld.text
            resetGame()
            generateNewGame()
        }
    }

    private fun checkWin(): Boolean {
        if(pruefe(f1 as TextView, fA1 as TextView, fB1 as TextView, fC1 as TextView, fD1 as TextView, fE1 as TextView, false)){f1.setBackgroundColor(Color.GREEN)}else{f1.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f2 as TextView, fA2 as TextView, fB2 as TextView, fC2 as TextView, fD2 as TextView, fE2 as TextView, false)){f2.setBackgroundColor(Color.GREEN)}else{f2.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f3 as TextView, fA3 as TextView, fB3 as TextView, fC3 as TextView, fD3 as TextView, fE3 as TextView, false)){f3.setBackgroundColor(Color.GREEN)}else{f3.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f4 as TextView, fA4 as TextView, fB4 as TextView, fC4 as TextView, fD4 as TextView, fE4 as TextView, false)){f4.setBackgroundColor(Color.GREEN)}else{f4.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(f5 as TextView, fA5 as TextView, fB5 as TextView, fC5 as TextView, fD5 as TextView, fE5 as TextView, false)){f5.setBackgroundColor(Color.GREEN)}else{f5.setBackgroundColor(Color.parseColor("#00ddff"))}

        if(pruefe(fA as TextView, fA1 as TextView, fA2 as TextView, fA3 as TextView, fA4 as TextView, fA5 as TextView, true)){fA.setBackgroundColor(Color.GREEN)}else{fA.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(fB as TextView, fB1 as TextView, fB2 as TextView, fB3 as TextView, fB4 as TextView, fB5 as TextView, true)){fB.setBackgroundColor(Color.GREEN)}else{fB.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(fC as TextView, fC1 as TextView, fC2 as TextView, fC3 as TextView, fC4 as TextView, fC5 as TextView, true)){fC.setBackgroundColor(Color.GREEN)}else{fC.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(fD as TextView, fD1 as TextView, fD2 as TextView, fD3 as TextView, fD4 as TextView, fD5 as TextView, true)){fD.setBackgroundColor(Color.GREEN)}else{fD.setBackgroundColor(Color.parseColor("#00ddff"))}
        if(pruefe(fE as TextView, fE1 as TextView, fE2 as TextView, fE3 as TextView, fE4 as TextView, fE5 as TextView, true)){fE.setBackgroundColor(Color.GREEN)}else{fE.setBackgroundColor(Color.parseColor("#00ddff"))}
        return (pruefe(f1 as TextView, fA1 as TextView, fB1 as TextView, fC1 as TextView, fD1 as TextView, fE1 as TextView, false)&&pruefe(f2 as TextView, fA2 as TextView, fB2 as TextView, fC2 as TextView, fD2 as TextView, fE2 as TextView, false)&&pruefe(f3 as TextView, fA3 as TextView, fB3 as TextView, fC3 as TextView, fD3 as TextView, fE3 as TextView, false)&&pruefe(f4 as TextView, fA4 as TextView, fB4 as TextView, fC4 as TextView, fD4 as TextView, fE4 as TextView, false)&&pruefe(f5 as TextView, fA5 as TextView, fB5 as TextView, fC5 as TextView, fD5 as TextView, fE5 as TextView, false)&&pruefe(fA as TextView, fA1 as TextView, fA2 as TextView, fA3 as TextView, fA4 as TextView, fA5 as TextView, true)&&pruefe(fB as TextView, fB1 as TextView, fB2 as TextView, fB3 as TextView, fB4 as TextView, fB5 as TextView, true)&&pruefe(fC as TextView, fC1 as TextView, fC2 as TextView, fC3 as TextView, fC4 as TextView, fC5 as TextView, true)&&pruefe(fD as TextView, fD1 as TextView, fD2 as TextView, fD3 as TextView, fD4 as TextView, fD5 as TextView, true)&&pruefe(fE as TextView, fE1 as TextView, fE2 as TextView, fE3 as TextView, fE4 as TextView, fE5 as TextView, true))
    }

    //Reset Funktion
    private fun resetGame(){
        val allFields = arrayOf(fA1, fB1, fC1, fD1, fE1, fA2, fB2, fC2, fD2, fE2, fA3, fB3, fC3, fD3, fE3, fA4, fB4, fC4, fD4, fE4, fA5, fB5, fC5, fD5, fE5)
        for(field in allFields){
            field.text=""
            field.setBackgroundColor(Color.WHITE)
        }
        var aussenFelder = arrayOf(f1, f2, f3, f4, f5, fA, fB, fC, fD, fE)
        for(field in aussenFelder){
            field.setBackgroundColor(Color.parseColor("#00ddff"))
        }
    }

    //Level sichern
    /*private fun winMoney(){
        val pref = getPreferences(Context.MODE_PRIVATE)
        val editor = pref.edit()
        var a = level.text.toString().toInt() + 15
        level.text = a.toString()
        editor.putInt("MONEY", a)
        editor.commit()
    }*/
    private fun lesen(): Int {
        val leser = datenbank.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM spieler WHERE name='Max'", null)
        ergebnis.moveToNext()
        val geldInt = ergebnis.getInt(ergebnis.getColumnIndex("geld"))
        level.text = "%d $" .format(geldInt)
        return geldInt
    }
    private fun aendern() {
        val schreiber = datenbank.writableDatabase
        val datensatz = ContentValues()
        geldValue += 15
        datensatz.put("geld", geldValue)
        schreiber.update(
            "spieler", datensatz, "name='Max'", null)
        level.text = "%d $" .format(geldValue)
    }

}
