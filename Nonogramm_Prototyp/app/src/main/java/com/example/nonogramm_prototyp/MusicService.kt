package com.example.nonogramm_prototyp

import android.R
import android.app.Service
import android.app.Service.START_NOT_STICKY
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.Toast

// nach der Anleitung von https://www.youtube.com/watch?v=nwPmuIrrsoA

class MusicService : Service(), MediaPlayer.OnErrorListener {
    private val mBinder: IBinder = ServiceBinder()
    var mPlayer: MediaPlayer? = null
    private var length = 0
    private val shopDB = ShopDatenbank(this)

    inner class ServiceBinder : Binder() {
        val service: MusicService
            get() = this@MusicService
    }

    override fun onBind(arg0: Intent?): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        var title = lesen("source")
        mPlayer = MediaPlayer.create(this, title)
        mPlayer!!.setOnErrorListener(this)
        if (mPlayer != null) {
            mPlayer!!.isLooping = true
            mPlayer!!.setVolume(50f, 50f)
        }
        mPlayer!!.setOnErrorListener(object : MediaPlayer.OnErrorListener {
            override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
                onError(mPlayer!!, what, extra)
                return true
            }
        })
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (mPlayer != null) {
            mPlayer!!.start()
        }
        return START_NOT_STICKY
    }

    fun pauseMusic() {
        if (mPlayer != null) {
            if (mPlayer!!.isPlaying) {
                mPlayer!!.pause()
                length = mPlayer!!.currentPosition
            }
        }
    }

    fun resumeMusic() {
        if (mPlayer != null) {
            if (!mPlayer!!.isPlaying) {
                mPlayer!!.seekTo(length)
                mPlayer!!.start()
            }
        }
    }

    fun startMusic() {
        var title = lesen("source")
        mPlayer = MediaPlayer.create(this, title)
        mPlayer!!.setOnErrorListener(this)
        if (mPlayer != null) {
            mPlayer!!.isLooping = true
            mPlayer!!.setVolume(50f, 50f)
            mPlayer!!.start()
        }
    }

    fun stopMusic() {
        if (mPlayer != null) {
            mPlayer!!.stop()
            mPlayer!!.release()
            mPlayer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPlayer != null) {
            try {
                mPlayer!!.stop()
                mPlayer!!.release()
            } finally {
                mPlayer = null
            }
        }
    }

    override fun onError(mp: MediaPlayer, what: Int, extra: Int): Boolean {
        Toast.makeText(this, "Music player failed", Toast.LENGTH_SHORT).show()
        if (mPlayer != null) {
            try {
                mPlayer!!.stop()
                mPlayer!!.release()
            } finally {
                mPlayer = null
            }
        }
        return false
    }

    private fun lesen(columnName: String):Int {
        val leser = shopDB.readableDatabase
        var daten = ""
        val ergebnis = leser.rawQuery(
            "SELECT * FROM music WHERE selected = 1", null)
        ergebnis.moveToNext()
        val tmpInt = ergebnis.getInt(ergebnis.getColumnIndex(columnName))
        leser.close()
        return tmpInt
    }
}