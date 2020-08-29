package com.example.nonogramm_prototyp

import android.content.ContentValues
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.zeit5x5.*
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.OneShotPreDrawListener.add
import kotlin.random.Random

class Zeit5x5: AppCompatActivity() {
    private var currentSymbol = "FILL"
    private var solution= arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)
    private val datenbank = DatenbankKlasse(this)
    private var geldValue = 0
    private val shopDB = ShopDatenbank(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.zeit5x5)
        geldValue = lesen()

        //Definition Felder
        val allFields = arrayOf(fA1, fB1, fC1, fD1, fE1, fA2, fB2, fC2, fD2, fE2, fA3, fB3, fC3, fD3, fE3, fA4, fB4, fC4, fD4, fE4, fA5, fB5, fC5, fD5, fE5)
        for(field in allFields){
            field.setOnClickListener(){
                onFieldClick(it as TextView)
            }
        }
        generateNewGame()

        //Farbe auslesen
        farbeAendern(statusText)
        farbeAendern(level)
        val leser = shopDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM color WHERE selected = 1", null)
        ergebnis.moveToNext()
        var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
        var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
        leser.close()
        ganzeSeite.setBackgroundColor(Color.parseColor(backgroundColor))




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

    private fun generateNewGame() {
        var i = 0
        while (i < 25) {
            solution[i] = Random.nextInt(2)
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

private fun beschrifte(summe: TextView, zahl1: Int, zahl2: Int, zahl3: Int, zahl4: Int, zahl5: Int, hoch: Boolean) { //: Array<Int>
var zahlen = arrayOf(solution[zahl1], solution[zahl2], solution[zahl3], solution[zahl4], solution[zahl5], 0)
var counter = 0
var ausgabe = arrayOf(0)
for(zahl in zahlen){
    if(zahl == 0){
        if(counter != 0){
           ausgabe = ausgabe.plus(counter)
            counter = 0
        }
        continue
    }

    counter++
}
var max = ausgabe.size-1
if(ausgabe.size != 1){ausgabe = ausgabe.sliceArray(1..max)}
if(hoch){summe.text = ausgabe.joinToString("\n")}
else{summe.text = ausgabe.joinToString(" ")}
}

private fun onFieldClick(field: TextView) {
if ((field.text == "FILL") || (field.text == "X")){
    field.setTextColor(Color.parseColor("#FFFFFF"))
    field.text = ""
    field.setBackgroundColor(Color.WHITE)
}
else if(currentSymbol == "X"){
    field.text = currentSymbol
    field.setTextColor(Color.parseColor("#F44336"))
}
else if(currentSymbol == "FILL"){
    field.setBackgroundColor(Color.GRAY)
    field.setTextColor(Color.GRAY)
    field.text = currentSymbol
}
if(checkWin()){
    Toast.makeText(this@Zeit5x5, getString(R.string.win), Toast.LENGTH_SHORT).show()
    aendern()
    //saveLevel()
    resetGame()
    generateNewGame()
}

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

private fun pruefe(summe: TextView, feld1: TextView, feld2: TextView, feld3: TextView, feld4: TextView, feld5: TextView, hoch: Boolean): Boolean { //: Array<Int>
var text = arrayOf(feld1.text, feld2.text, feld3.text, feld4.text, feld5.text, 0)
var tmptext: String
var counter = 0
var ausgabe = arrayOf(0)
for(zahl in text){
    if(zahl != "FILL"){
        if(counter != 0){
            ausgabe = ausgabe.plus(counter)
            counter = 0
        }
        continue
    }

    counter++
}
var max = ausgabe.size-1
if(ausgabe.size != 1){ausgabe = ausgabe.sliceArray(1..max)}
if(hoch){tmptext = ausgabe.joinToString("\n")}
else{tmptext = ausgabe.joinToString(" ")}
//if(tmptext == summe.text){ summe.setBackgroundColor(Color.GREEN)}else{summe.setBackgroundColor(Color.parseColor("#00ddff"))}
return tmptext == summe.text
}


private fun checkWin():Boolean{
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
    private fun lesen(): Int {
        val leser = datenbank.readableDatabase
        var daten = ""
        val ergebnis = leser.rawQuery(
            "SELECT * FROM spieler", null)
        ergebnis.moveToNext()
        val geldInt = ergebnis.getInt(ergebnis.getColumnIndex("geld"))
        daten += "%d $" .format(geldInt)
       level.text = daten
        return geldInt
    }
    private fun aendern() {
        val schreiber = datenbank.writableDatabase
        val datensatz = ContentValues()
        geldValue += 5
        datensatz.put("geld", geldValue)
        schreiber.update(
            "spieler", datensatz, "name='Max'", null)
        level.text = "%d $" .format(geldValue)
    }

}