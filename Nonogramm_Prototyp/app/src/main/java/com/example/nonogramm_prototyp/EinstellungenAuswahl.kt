package com.example.nonogramm_prototyp

import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.einstellungen_auswahl.*

class EinstellungenAuswahl: AppCompatActivity() {
    private val shopDB = ShopDatenbank(this)
    private val db = DatenbankKlasse(this)
    private lateinit var vorbutton: Button
    private lateinit var nachbutton: Button
    private var zahl = 0
    private var zahlVor = 6
    private var zahlNach = 0
    private lateinit var dbString:String
    var daten = ""
    var seiteZahl = 1

    //Musik
    private var mIsBound = false
    private var mServ: MusicService? = null
    private val Scon: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            mServ = (binder as MusicService.ServiceBinder).service
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mServ = null
        }
    }

    fun doBindService() {
        bindService(
            Intent(this, MusicService::class.java),
            Scon, Context.BIND_AUTO_CREATE
        )
        mIsBound = true
    }

    fun doUnbindService() {
        if (mIsBound) {
            unbindService(Scon)
            mIsBound = false
        }
    }
    override fun onResume() {
        super.onResume()
        if (mServ != null) {
            mServ!!.resumeMusic()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        doUnbindService()
        val music = Intent()
        music.setClass(this, MusicService::class.java)
        //stopService(music)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.story_auswahl)
        dbString = intent.getStringExtra("db")
        vorbutton = findViewById(R.id.vor_button)
        nachbutton = findViewById(R.id.zurueck_button)
        loadValues()
        doBindService()
        val music = Intent()
        music.setClass(this@EinstellungenAuswahl, MusicService::class.java)
        //startService(music)

        farbeAendern(seite)
        val leser = shopDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM color WHERE selected = 1", null)
        ergebnis.moveToNext()
        var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
        var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
        leser.close()
        //ganzeSeite.setTextColor(Color.parseColor(fontColor.toString()))
        ganzeSeite.setBackgroundColor(Color.parseColor(backgroundColor))

        var allFields = arrayOf(feld1, feld2, feld3, feld4, feld5, feld6)
        for(field in allFields){
            field.setOnClickListener(){
                onFieldClick(it as TextView)
            }
        }
        seite.text = "%d" .format(seiteZahl)

        vorbutton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                if(zahl != zahlVor){
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
    private fun austauschen(field: TextView) {
        val schreiber = shopDB.writableDatabase
        val datensatz = ContentValues()
        datensatz.put("selected", false)
        schreiber.update(
            dbString, datensatz, "selected=1" , null)
        schreiber.close()
        val schreiber2 = shopDB.writableDatabase
        val datensatz2 = ContentValues()
        datensatz2.put("selected", true)
        schreiber2.update(
            dbString, datensatz2, "name='%s'" .format(field.text), null)
        schreiber2.close()
    }
    private fun aendern(auswahl:String, value: Boolean) {
        val schreiber = db.writableDatabase
        val datensatz = ContentValues()
        datensatz.put(auswahl, value)
        schreiber.update(
            "spieler", datensatz, "name='Max'", null)
        schreiber.close()
    }

    private fun onFieldClick(field: TextView) {
        if(field.text == "----" || field.text == "????"){
            Toast.makeText(this@EinstellungenAuswahl, "Element nicht verfügbar", Toast.LENGTH_SHORT).show()
        }
        else{
            austauschen(field)
            if(dbString == "music"){
                aendern("musikan", true)
                mServ?.stopMusic()

               startMusik()
            }
            else{
                farbeAendern(seite)
                val leser = shopDB.readableDatabase
                val ergebnis = leser.rawQuery(
                    "SELECT * FROM color WHERE selected = 1", null)
                ergebnis.moveToNext()
                var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
                var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
                leser.close()
                //ganzeSeite.setTextColor(Color.parseColor(fontColor.toString()))
                ganzeSeite.setBackgroundColor(Color.parseColor(backgroundColor))
            }
        }
        loadValues()
    }

    private fun startMusik() {
        doBindService()
        val music = Intent()
        music.setClass(this@EinstellungenAuswahl, MusicService::class.java)
        startService(music)
        loadValues()
        mServ?.startMusic()
    }

    private fun loadValues(){
        var allFields = arrayOf(feld1, feld2, feld3, feld4, feld5, feld6)
        val leser = shopDB.readableDatabase
        var tmp = ""
        for(field in allFields){
            field.text = "----"
            field.setBackgroundColor(Color.parseColor("#aaaaaa")) //#00ddff
            field.setTextColor(Color.parseColor("#000000"))
        }
        val ergebnis = leser.rawQuery(
            "SELECT * FROM "+ dbString + " WHERE id > %d" .format(zahl), null)
            if(ergebnis.moveToNext()){
                tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
                if(ergebnis.getString((ergebnis.getColumnIndex("gekauft"))) == "1"){
                    feld1.setBackgroundColor(Color.parseColor("#00ddff"))
                    if(ergebnis.getString((ergebnis.getColumnIndex("selected"))) == "1"){feld1.setBackgroundColor(Color.GREEN)}
                    if(dbString== "color"){
                        feld1.setBackgroundColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("background"))))
                        feld1.setTextColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("font"))))
                    }
                }
                else{tmp = "????"}
                daten = "%s" .format(tmp)
                feld1.text = daten
            }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("gekauft"))) == "1"){
                feld2.setBackgroundColor(Color.parseColor("#00ddff"))
                if(ergebnis.getString((ergebnis.getColumnIndex("selected"))) == "1"){feld2.setBackgroundColor(Color.GREEN)}
                if(dbString== "color"){
                    feld2.setBackgroundColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("background"))))
                    feld2.setTextColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("font"))))
                }
            }
            else{tmp = "????"}
            daten = "%s" .format(tmp)
            feld2.text = daten
        }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("gekauft"))) == "1"){
                feld3.setBackgroundColor(Color.parseColor("#00ddff"))
                if(ergebnis.getString((ergebnis.getColumnIndex("selected"))) == "1"){feld3.setBackgroundColor(Color.GREEN)}
                if(dbString== "color"){
                    feld3.setBackgroundColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("background"))))
                    feld3.setTextColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("font"))))
                }
            }
            else{tmp = "????"}
            daten = "%s" .format(tmp)
            feld3.text = daten
        }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("gekauft"))) == "1"){
                feld4.setBackgroundColor(Color.parseColor("#00ddff"))
                if(ergebnis.getString((ergebnis.getColumnIndex("selected"))) == "1"){feld4.setBackgroundColor(Color.GREEN)}
                if(dbString== "color"){
                    feld4.setBackgroundColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("background"))))
                    feld4.setTextColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("font"))))
                }
            }
            else{tmp = "????"}
            daten = "%s" .format(tmp)
            feld4.text = daten
        }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("gekauft"))) == "1"){
                feld5.setBackgroundColor(Color.parseColor("#00ddff"))
                if(ergebnis.getString((ergebnis.getColumnIndex("selected"))) == "1"){feld5.setBackgroundColor(Color.GREEN)}
                if(dbString== "color"){
                    feld5.setBackgroundColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("background"))))
                    feld5.setTextColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("font"))))
                }
            }
            else{tmp = "????"}
            daten = "%s" .format(tmp)
            feld5.text = daten
        }
        if(ergebnis.moveToNext()){
            tmp = ergebnis.getString((ergebnis.getColumnIndex("name")))
            if(ergebnis.getString((ergebnis.getColumnIndex("gekauft"))) == "1"){
                feld6.setBackgroundColor(Color.parseColor("#00ddff"))
                if(ergebnis.getString((ergebnis.getColumnIndex("selected"))) == "1"){feld6.setBackgroundColor(Color.GREEN)}
                if(dbString== "color"){
                    feld6.setBackgroundColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("background"))))
                    feld6.setTextColor(Color.parseColor(ergebnis.getString(ergebnis.getColumnIndex("font"))))
                }
            }
            else{tmp = "????"}
            daten = "%s" .format(tmp)
            feld6.text = daten
            zahlVor = ergebnis.getInt((ergebnis.getColumnIndex("id")))
        }

        leser.close()
    }
}
