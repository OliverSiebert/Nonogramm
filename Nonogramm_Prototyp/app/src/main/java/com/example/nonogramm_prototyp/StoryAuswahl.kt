package com.example.nonogramm_prototyp

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.story_auswahl.*

class StoryAuswahl: AppCompatActivity() {
    private val storyDB = StoryDatenbank(this)
    private lateinit var vorbutton: Button
    private lateinit var nachbutton: Button
    private var zahl = 0
    private var zahlVor = 6
    private var zahlNach = 0
    var daten = ""
    var seiteZahl = 1
    private val shopDB = ShopDatenbank(this)

    override fun onRestart() {
        super.onRestart()
        loadValues()
    }

    // 1. Beim Erstellen der Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.story_auswahl)
        vorbutton = findViewById(R.id.vor_button)
        nachbutton = findViewById(R.id.zurueck_button)
        //1.1 Daten laden
        loadValues()
        //1.2 Knopfdruck
        var allFields = arrayOf(feld1, feld2, feld3, feld4, feld5, feld6)
        for(field in allFields){
            field.setOnClickListener(){
                onFieldClick(it as TextView)
            }
        }
        seite.text = "%d" .format(seiteZahl)
        //1.3 Farbe aendern
        farbeAendern(seite)
        val leser = shopDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM color WHERE selected = 1", null)
        ergebnis.moveToNext()
        var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
        var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
        leser.close()
        ganzeSeite.setBackgroundColor(Color.parseColor(backgroundColor))

        //1.4 Vor- und Nach-Button
        vorbutton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
               if(zahl != zahlVor) {
                   zahlNach = zahl
                   seiteZahl += 1
                   seite.text = "%d " .format(seiteZahl)
               }
                zahl = zahlVor
                loadValues()
            }

        })
        nachbutton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                zahlVor = zahl
                zahl = zahlNach
                zahlNach = zahl-6
                if(zahl > -1){
                    seiteZahl -= 1
                    seite.text = "%d " .format(seiteZahl)
                }
                loadValues()
            }

        })
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

    private fun onFieldClick(field: TextView) {
        if(field.text != "----"){
            aendern(field)
            val storyintent = Intent(this@StoryAuswahl, Story5x5::class.java);
            startActivity(storyintent);
        }
    }
    private fun aendern(field: TextView) {
        val schreiber = storyDB.writableDatabase
        val datensatz = ContentValues()
        datensatz.put("selected", true)
        schreiber.update(
            "story", datensatz, "name='%s'" .format(field.text), null)
        schreiber.close()
    }
    private fun anzahlRows():Int{
        val leser = storyDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT COUNT(*) FROM story" , null)
        return ergebnis.getInt(ergebnis.getColumnIndex("COUNT"))
    }

    private fun loadValues() {
        var allFields = arrayOf(feld1, feld2, feld3, feld4, feld5, feld6)
        val leser = storyDB.readableDatabase
        var tmp = ""
        for(field in allFields){
            field.text = "----"
            field.setBackgroundColor(Color.parseColor("#00ddff"))
        }
        val ergebnis = leser.rawQuery(
            "SELECT * FROM story WHERE id > %d" .format(zahl), null)
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("solved"))) == "1"){ feld1.setBackgroundColor(
                Color.GREEN)}
            daten = "%s" .format(tmp)
            feld1.text = daten
        }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("solved"))) == "1"){ feld2.setBackgroundColor(
                Color.GREEN)}
            daten = "%s" .format(tmp)
            feld2.text = daten
        }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("solved"))) == "1"){ feld3.setBackgroundColor(
                Color.GREEN)}
            daten = "%s" .format(tmp)
            feld3.text = daten
        }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("solved"))) == "1"){ feld4.setBackgroundColor(
                Color.GREEN)}
            daten = "%s" .format(tmp)
            feld4.text = daten
        }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("solved"))) == "1"){ feld5.setBackgroundColor(
                Color.GREEN)}
            daten = "%s" .format(tmp)
            feld5.text = daten
        }
        if(ergebnis.moveToNext()) {
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("solved"))) == "1"){ feld6.setBackgroundColor(
                Color.GREEN)}
            daten = "%s".format(tmp)
            feld6.text = daten
            zahlVor = ergebnis.getInt((ergebnis.getColumnIndex("id")))
        }
        leser.close()

    }
}