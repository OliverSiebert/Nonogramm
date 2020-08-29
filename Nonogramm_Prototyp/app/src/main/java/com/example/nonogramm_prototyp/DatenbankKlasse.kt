package com.example.nonogramm_prototyp
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

 class DatenbankKlasse(context:Context):
    SQLiteOpenHelper(context, "spieler.db", null, 1){
        override fun onCreate(db: SQLiteDatabase){
            db.execSQL("CREATE TABLE [spieler]" +
            "([id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "[name] TEXT NOT NULL, [geld] INTEGER NOT NULL, [musikan] BOOLEAN NOT NULL)")
        }
        override fun onUpgrade(db: SQLiteDatabase,
                               versionAlt: Int, versionNeu:Int){

        }

}
