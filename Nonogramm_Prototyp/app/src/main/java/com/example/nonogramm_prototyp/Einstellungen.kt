package com.example.nonogramm_prototyp

import android.content.*
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import kotlinx.android.synthetic.main.einstellungen.*

class Einstellungen: AppCompatActivity() {
    private val db = DatenbankKlasse(this)
    private lateinit var farb_button:TextView
    private lateinit var musik_button: TextView
    private lateinit var musik_switch: Button
    private lateinit var mp:MediaPlayer
    private val shopDB = ShopDatenbank(this)


    //Musik abspielen

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
        stopService(music)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.einstellungen)
        farb_button= findViewById(R.id.farbwechsel)
        musik_button= findViewById(R.id.musikwechsel)
        musik_switch= findViewById(R.id.musikswitch)

        doBindService()
        val music = Intent()
        music.setClass(this@Einstellungen, MusicService::class.java)

        val leser = shopDB.readableDatabase
        val ergebnis = leser.rawQuery(
            "SELECT * FROM color WHERE selected = 1", null)
        ergebnis.moveToNext()
        var fontColor = ergebnis.getString(ergebnis.getColumnIndex("font"))
        var backgroundColor = ergebnis.getString(ergebnis.getColumnIndex("background"))
        leser.close()
        ganzeSeite.setBackgroundColor(Color.parseColor(backgroundColor))

        farb_button.text = "Colors"
        musik_button.text = "Music"
        if(lesen("musikan") == 1){
            musik_switch.text = "Musik an"
            musik_switch.setBackgroundColor(Color.GREEN)
        }else{
            musik_switch.text = "Musik aus"
            musik_switch.setBackgroundColor(Color.RED)
        }
        farb_button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View): Unit {
                //aendern("farbwechsel", true)
                val intent = Intent(this@Einstellungen, EinstellungenAuswahl::class.java);
                intent.putExtra("db", "color")
                startActivity(intent);
            }
        })
        musik_button.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View): Unit {
                //aendern("musikwechsel", true)
                val intent = Intent(this@Einstellungen, EinstellungenAuswahl::class.java);
                intent.putExtra("db", "music")
                startActivity(intent);
            }
        })
        musik_switch.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View): Unit {
                if(musik_switch.text == "Musik aus"){
                    aendern("musikan", true)
                    musik_switch.text = "Musik an"
                    musik_switch.setBackgroundColor(Color.GREEN)
                    startService(music)

                }
                else if(musik_switch.text == "Musik an"){
                    aendern("musikan", false)
                    musik_switch.text = "Musik aus"
                    musik_switch.setBackgroundColor(Color.RED)
                    mServ?.pauseMusic()
                    //doBindService()
                    //val music = Intent()
                    //music.setClass(this@Einstellungen, MusicService::class.java)
                }
            }
        })
        /*musik_switch.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked){
                aendern("musikspielt", true)
                var test = R.raw.song1
                mp = MediaPlayer.create(this, test)
                musik.setMP(mp)
                musik.an()
                aendern("musikan", true)
            }else{
                aendern("musikspielt", false)
               musik.aus()
                aendern("musikan", false)
            }
        }*/
    }
    private fun lesen(columnName: String):Int {
        val leser = db.readableDatabase
        var daten = ""
        val ergebnis = leser.rawQuery(
            "SELECT * FROM spieler", null)
        ergebnis.moveToNext()
        val tmpInt = ergebnis.getInt(ergebnis.getColumnIndex(columnName))
        leser.close()
        return tmpInt
    }

    private fun aendern(auswahl:String, value: Boolean) {
        val schreiber = db.writableDatabase
        val datensatz = ContentValues()
        datensatz.put(auswahl, value)
        schreiber.update(
            "spieler", datensatz, "name='Max'", null)
        schreiber.close()
    }
}