package com.example.nonogramm_prototyp

import android.content.ContentValues
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.shop.*
import kotlin.random.Random

class Shop: AppCompatActivity() {
    private var shopItems = arrayOf(1,1,1)
    private var colorMax = 57
    private var musicMax = 17
    private var daten = ""
    private var nameArray = arrayOf("", "", "")
    private var preisArray = arrayOf(0, 0, 0)
    private val shopDB = ShopDatenbank(this)
    private val datenbank = DatenbankKlasse(this)
    private var geldValue = 0
    private var geld = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop)
        geld = lesen()
        generateItems()
        //shopItem1.text = shopItems[0].toString()
        //shopItem2.text = shopItems[1].toString()
        //shopItem3.text = shopItems[2].toString()
        loadValues()
        var allFields = arrayOf(shopItem1, shopItem2, shopItem3)
        for(field in allFields){
            field.setOnClickListener(){
                onFieldClick(it as TextView)
            }
        }
        refresh.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?):Unit {
                generateItems()
                loadValues()
            }
        })
        farbeAendern(level)
        val leser = shopDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM color WHERE selected = 1", null)
        ergebnis.moveToNext()
        var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
        var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
        leser.close()
        //ganzeSeite.setTextColor(Color.parseColor(fontColor.toString()))
        ganzeSeite.setBackgroundColor(Color.parseColor(backgroundColor))
        ersteRow.setBackgroundColor(Color.parseColor(backgroundColor))
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

    private fun onFieldClick(field: TextView) {
        if(field.text == nameArray[0] || field.text == nameArray[1] || field.text == nameArray[2]){
            Toast.makeText(this@Shop, "Element bereits gekauft", Toast.LENGTH_SHORT).show()
        }
        else{
            val meldung = AlertDialog.Builder(this)
            meldung.setMessage("Möchten Sie das Element wirklich kaufen?")
            meldung.setTitle("Shop")
            meldung.setPositiveButton("Ja"){_, _ ->
                kaufen(field)}
            meldung.setNegativeButton("Nein"){_, _ -> }
            meldung.setNeutralButton("Abbrechen"){_, _ -> }
            meldung.show()
        }
    }

    private fun kaufen(field: TextView) {
        val schreiber = datenbank.writableDatabase
        val datensatz = ContentValues()
        var tmpZahl = -1
        var allFields = arrayOf(shopItem1, shopItem2, shopItem3)
        var i = 0
        while(i<3){
            if(field.text == nameArray[i] + ": " + preisArray[i] + "$"){
                tmpZahl = i
            }
            i += 1
        }
        //level.text = nameArray[tmpZahl] + ": " + preisArray[tmpZahl] + "$"
        if(preisArray[tmpZahl] > geld){
            Toast.makeText(this@Shop, "zu wenig Geld", Toast.LENGTH_SHORT).show()
            //level.text = preisArray[tmpZahl].toString()
        }
        else{
            geld = geld - preisArray[tmpZahl]
            datensatz.put("geld", geld)
            schreiber.update(
                "spieler", datensatz, "name='Max'", null)
            //level.text = "%d $" .format(geld)
            schreiber.close()

            val schreiberShop = shopDB.writableDatabase
            var datensatzShop = ContentValues()
            datensatzShop.put("gekauft", true)
            if(field == shopItem1 || field == shopItem2) {
                schreiberShop.update(
                    "color", datensatzShop, "name ='%s'".format(nameArray[tmpZahl]), null
                )
            }
            else{
                schreiberShop.update(
                    "music", datensatzShop, "name ='%s'".format(nameArray[tmpZahl]), null
                )
            }
            schreiberShop.close()
            loadValues()
            geld = lesen()
        }


    }

    private fun generateItems() {
        // Zufallszahl
        var i = 0
        while (i < 2) {
            shopItems[i] = Random.nextInt(colorMax) + 1
            i++
        }
        shopItems[2] = Random.nextInt(musicMax) + 1
        if(shopItems[0] == shopItems[1]){
            generateItems()
        }

    }
    private fun loadValues(){
        var allFields = arrayOf(shopItem1, shopItem2, shopItem3)
        val leser = shopDB.readableDatabase
        var tmp = ""
        var i= 0
        for(field in allFields){
            var ergebnis = leser.rawQuery(
                "SELECT * FROM color WHERE id = %d" .format(shopItems[i]), null)
            if(ergebnis.moveToNext()){
                nameArray[i] = ergebnis.getString((ergebnis.getColumnIndex("name")))
                preisArray[i] = ergebnis.getInt((ergebnis.getColumnIndex("preis")))
                if(ergebnis.getString((ergebnis.getColumnIndex("gekauft"))) == "1"){
                    field.setBackgroundColor(Color.parseColor("#aaaaaa"))
                    field.setTextColor(Color.parseColor("#000000"))
                    daten = ergebnis.getString(ergebnis.getColumnIndex("name"))
                }
                else{
                    field.setBackgroundColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("background"))))
                    field.setTextColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("font"))))
                    daten = ergebnis.getString(ergebnis.getColumnIndex("name")) + ": " + ergebnis.getString(ergebnis.getColumnIndex("preis")) + "$"
                }
                i += 1
                field.text = daten
            }
        }
        var ergebnis = leser.rawQuery(
            "SELECT * FROM music WHERE id = %d" .format(shopItems[2]), null)
        if(ergebnis.moveToNext()){
            nameArray[2] = ergebnis.getString((ergebnis.getColumnIndex("name")))
            preisArray[2] = ergebnis.getInt((ergebnis.getColumnIndex("preis")))
            shopItem3.setTextColor(Color.parseColor("#000000"))
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("gekauft"))) == "1"){
                shopItem3.setBackgroundColor(Color.parseColor("#aaaaaa"))
                daten = ergebnis.getString(ergebnis.getColumnIndex("name"))
            }
            else{
                shopItem3.setBackgroundColor(Color.parseColor("#00ddff"))
                daten = ergebnis.getString(ergebnis.getColumnIndex("name")) + ": " + ergebnis.getString(ergebnis.getColumnIndex("preis")) + "$"
            }
            shopItem3.text = daten
        }
    }
}