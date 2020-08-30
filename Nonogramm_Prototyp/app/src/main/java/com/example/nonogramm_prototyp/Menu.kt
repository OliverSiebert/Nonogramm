package com.example.nonogramm_prototyp

import android.content.*
import android.graphics.Color
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.example.nonogramm_prototyp.MusicService.ServiceBinder
import kotlinx.android.synthetic.main.menu_layout.*


class Menu: AppCompatActivity() {
    private val datenbank = DatenbankKlasse(this)
    private val shopDB = ShopDatenbank(this)
    private val storyDb = StoryDatenbank(this)
    private lateinit var zeitmodus_button: Button
    private lateinit var farbmodus_button: Button
    private lateinit var storymodus_button: Button
    private lateinit var shop_button: Button
    private lateinit var einstellungen_button:Button
    private lateinit var info_button:Button
    private var musikan=0


    // 1. Zum Musik abspielen

    private var mIsBound = false
    private var mServ: MusicService? = null
    private val Scon: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, binder: IBinder) {
            mServ = (binder as ServiceBinder).service
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
        music.setClass(this@Menu, MusicService::class.java)
        stopService(music)
    }



    // 2. Beim Erstellen der Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_layout)

        //Beim ersten Starten
        var prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        var firstStart = prefs.getBoolean("firstStart", true)

        if(firstStart){
            initDatenbank();
        }

        geld.text= "%d $" .format(lesen("geld"))

        //Musik spielen
        if(lesen("musikan") == 1){
            doBindService()
            val music = Intent()
            music.setClass(this, MusicService::class.java)
            startService(music)

        }

        //Farbe auslesen
        farbeAendern(geld)
        val leser = shopDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM color WHERE selected = 1", null)
        ergebnis.moveToNext()
        var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
        var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
        leser.close()
        ganzeSeite.setBackgroundColor(Color.parseColor(backgroundColor))

        //Buttondefinition
        zeitmodus_button = findViewById(R.id.bu_zeitmodus)
        farbmodus_button = findViewById(R.id.bu_farbenmodus)
        storymodus_button= findViewById(R.id.bu_storymodus)
        shop_button = findViewById(R.id.bu_shop)
        einstellungen_button = findViewById(R.id.bu_einstellungen)
        info_button = findViewById(R.id.info)

        // Seitenwechsel
        zeitmodus_button.setOnClickListener(object: View.OnClickListener {
            var modus:Int = 0
            override fun onClick(view: View): Unit {
                val meldung = AlertDialog.Builder(this@Menu)
                meldung.setMessage("In welcher Größe wollen Sie spielen?")
                meldung.setTitle("Münzmodus")
                meldung.setPositiveButton("3x3"){_, _ ->
                    startActivity(Intent(this@Menu, Zeit3x3::class.java));}
                meldung.setNegativeButton("5x5"){_, _ ->
                    startActivity(Intent(this@Menu, Zeit5x5::class.java));
                }
                meldung.setNeutralButton("Abbrechen"){_, _ -> }
                meldung.show()

            }

        })

        farbmodus_button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View): Unit {
                val farbintent = Intent(this@Menu, Farb5x5::class.java);
                startActivity(farbintent);
            }
        })
        // Seitenwechsel Ende
        storymodus_button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View): Unit {
                val storyintent = Intent(this@Menu, StoryAuswahl::class.java);
                startActivity(storyintent);
            }
        })
        shop_button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View): Unit {
                val shopintent = Intent(this@Menu, Shop::class.java);
                startActivity(shopintent);
            }
        })
        einstellungen_button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View): Unit {
                val intent = Intent(this@Menu, Einstellungen::class.java);
                startActivity(intent);
            }
        })
        info_button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View): Unit {
                val meldung = AlertDialog.Builder(this@Menu)
                meldung.setTitle("Spielerklärung:")
                meldung.setMessage("MÜNZMODUS: Ziel ist es, die Zellen eines Gitters so einzufärben (bzw. nicht einzufärben), dass die eingefärbten Kästchen in jeder Zeile und Spalte der dafür angegebenen Anzahl und Gliederung entsprechen. Aus der Kombination von Zeilen- und Spaltenangaben lässt sich eine (meist eindeutige) Lösung logisch herleiten.\n\nSTORYMODUS: Hier gibt es nur eine richtige Lösung. Die ausgefüllten Kästchen müssen einem bestimmten Muster bzw. Bild entsprechen. \n\nFARBMODUS: Hier ist zwischen verschiedenfarbigen Blöcken kein leeres Kästchen notwendig. Treffen Blöcke der gleichen Farbe aufeinander, muss wie bei den einfarbigen Nonogrammen mindestens ein leeres Kästchen eingezeichnet werden. R:2 O:1 Y:2 bedeutet, dass zwei rote Blöcke gefolgt von einem orangen und zwei gelben Blöcke als Lösung gelten.")
                meldung.setNeutralButton("Abbrechen"){_, _ -> }
                meldung.show()
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

    private fun lesen(columnName: String):Int {
        val leser = datenbank.readableDatabase
        var daten = ""
        val ergebnis = leser.rawQuery(
            "SELECT * FROM spieler", null)
        ergebnis.moveToNext()
        val geldInt = ergebnis.getInt(ergebnis.getColumnIndex("geld"))
        daten = "%d $" .format(geldInt)
        geld.text = daten
        musikan = ergebnis.getInt(ergebnis.getColumnIndex("musikan"))
        return ergebnis.getInt(ergebnis.getColumnIndex(columnName))
        leser.close()

    }

    private fun initDatenbank() {
        val schreiber = datenbank.writableDatabase
        val datensatz = ContentValues()
        datensatz.put("name", "Max")
        datensatz.put("geld", 500)
        datensatz.put("musikan", true)         // spielt gerade Musik
        if(schreiber.insert(
                "spieler", null, datensatz) == -1L){
            geld.text = "Fehler beim Hinzufügen"
        }
        else{
            geld.text = "Daten"
        }
        shopDB.initDatabase()
        storyDb.initDatabase()



        // beim ersten Starten
        var prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        var editor = prefs.edit()
        editor.putBoolean("firstStart", false)
        editor.apply()
        schreiber.close()

    }

    override fun onStop(){
        super.onStop()
        datenbank.close()
    }

}


