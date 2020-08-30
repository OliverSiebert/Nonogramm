package com.example.nonogramm_prototyp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ShopDatenbank(context:Context):
    SQLiteOpenHelper(context, "color.db", null, 1){
    var counterColor =0
    var counterMusic =0
    override fun onCreate(db: SQLiteDatabase){
        db.execSQL(
            "CREATE TABLE [color]" +
                    "([id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "[name] TEXT NOT NULL, [preis] INTEGER NOT NULL, [selected] BOOLEAN NOT NULL, [gekauft] BOOLEAN NOT NULL, [background] TEXT NOT NULL, [font] TEXT NOT NULL)"
        )
        db.execSQL(
            "CREATE TABLE [music]" +
                    "([id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "[name] TEXT NOT NULL, [preis] INTEGER NOT NULL, [selected] BOOLEAN NOT NULL, [gekauft] BOOLEAN NOT NULL, [source] INT NOT NULL)"
        )
    }

    fun initDatabase(){

        // DB COLOR  Values
        //change colorMax in Shop.kt
        counterColor += insertColor(1,"White", 100, true, true, "#FFFFFF", "#000000")
        counterColor += insertColor(2, "Red", 100, false, true, "#FF0000", "#000000")
        counterColor += insertColor(3, "Orange", 100, false, true, "#FFA500", "#000000")
        counterColor += insertColor(4,"Yellow", 100, false, true, "#FFFF00", "#000000")
        counterColor += insertColor(5,"Green", 100, false, true, "#00FF00", "#000000")
        counterColor += insertColor(6,"Blue", 100, false, true, "#0000FF", "#FFFFFF")
        counterColor += insertColor(7,"Purple", 100, false, true, "#A020F0", "#FFFFFF")
        counterColor += insertColor(8,"Gray", 100, false, true, "#BEBEBE", "#000000")
        counterColor += insertColor(9,"CadetBlue", 100, false, false, "#5F9EA0", "#000000")
        counterColor += insertColor(10,"DeepSkyBlue", 100, false, false, "#00BFFF", "#000000")
        counterColor += insertColor(11,"DodgerBlue", 100, false, false, "#1E90FF", "#000000")
        counterColor += insertColor(12,"LightBlue", 100, false, false, "#ADD8E6", "#000000")
        counterColor += insertColor(13,"LightCyan", 100, false, false, "#E0FFFF", "#000000")
        counterColor += insertColor(14,"LightSkyBlue", 100, false, false, "#87CEFA", "#000000")
        counterColor += insertColor(15,"LightSteelBlue", 100, false, false, "#B0C4DE", "#000000")
        counterColor += insertColor(16,"PaleTurquoise", 100, false, false, "#AFEEEE", "#000000")
        counterColor += insertColor(17,"RoyalBlue", 100, false, false, "#4169E1", "#000000")
        counterColor += insertColor(18,"SkyBlue", 100, false, false, "#87CEEB", "#000000")
        counterColor += insertColor(19,"SlateBlue", 100, false, false, "#6A5ACD", "#000000")
        counterColor += insertColor(20,"SteelBlue", 100, false, false, "#4682B4", "#000000")
        counterColor += insertColor(21,"Aquamarine", 100, false, false, "#7FFFD4", "#000000")
        counterColor += insertColor(22,"Cyan", 100, false, false, "#00FFFF", "#000000")
        counterColor += insertColor(23,"Turquoise", 100, false, false, "#40E0D0", "#000000")
        counterColor += insertColor(24,"Navy", 100, false, false, "#000080", "#FFFFFF")
        counterColor += insertColor(25,"RosyBrown", 100, false, false, "#BC8F8F", "#000000")
        counterColor += insertColor(26,"SandyBrown", 100, false, false, "#F4A460", "#000000")
        counterColor += insertColor(27,"Beige", 100, false, false, "#F5F5DC", "#000000")
        counterColor += insertColor(28,"Brown", 100, false, false, "#A52A2A", "#FFFFFF")
        counterColor += insertColor(29,"Burlywood", 100, false, false, "#DEB887", "#000000")
        counterColor += insertColor(30,"Chocolate", 100, false, false, "#D2691E", "#000000")
        counterColor += insertColor(31,"Peru", 100, false, false, "#CD853F", "#000000")
        counterColor += insertColor(32,"DarkSlateGray", 100, false, false, "#2F4F4F", "#FFFFFF")
        counterColor += insertColor(33,"LightSlateGray", 100, false, false, "#778899", "#000000")
        counterColor += insertColor(34,"SlateGray", 100, false, false, "#708090", "#000000")
        counterColor += insertColor(35,"Khaki", 100, false, false, "#F0E68C", "#000000")
        counterColor += insertColor(36,"OliveDrab", 100, false, false, "#6B8E23", "#000000")
        counterColor += insertColor(37,"DarkGreen", 100, false, false, "#006400", "#FFFFFF")
        counterColor += insertColor(38,"ForestGreen", 100, false, false, "#228B22", "#FFFFFF")
        counterColor += insertColor(39, "SeaGreen", 100, false, false, "#2E8B57", "#FFFFFF")
        counterColor += insertColor(40, "Coral", 100, false, false, "#FF7F50", "#000000")
        counterColor += insertColor(41, "Salmon", 100, false, false, "#FA8072", "#000000")
        counterColor += insertColor(42, "Bisque", 100, false, false, "#FFE4C4", "#000000")
        counterColor += insertColor(43, "Sienna", 100, false, false, "#A0522D", "#000000")
        counterColor += insertColor(44, "DarkRed", 100, false, false, "#8B0000", "#FFFFFF")
        counterColor += insertColor(45, "DeepPink", 100, false, false, "#FF1493", "#FFFFFF")
        counterColor += insertColor(46, "HotPink", 100, false, false, "#FF69B4", "#000000")
        counterColor += insertColor(47, "VioletRed", 100, false, false, "#D02090", "#000000")
        counterColor += insertColor(48, "Firebrick", 100, false, false, "#B22222", "#FFFFFF")
        counterColor += insertColor(49, "Tomato", 100, false, false, "#FF6347", "#000000")
        counterColor += insertColor(50, "DarkMagenta", 100, false, false, "#8B008B", "#FFFFFF")
        counterColor += insertColor(51, "Violet", 100, false, false, "#EE82EE", "#FFFFFF")
        counterColor += insertColor(52, "Magenta", 100, false, false, "#FF00FF", "#000000")
        counterColor += insertColor(53, "Maroon", 100, false, false, "#B03060", "#FFFFFF")
        counterColor += insertColor(54, "Orchid", 100, false, false, "#DA70D6", "#000000")
        counterColor += insertColor(55, "Plum", 100, false, false, "#DDA0DD", "#000000")
        counterColor += insertColor(56, "NavajoWhite", 100, false, false, "#FFDEAD", "#000000")
        counterColor += insertColor(57, "Gold", 100, false, false, "#FFD700", "#000000")


        // DB MUSIC
        //change musicMax in Shop.kt
        // add mp3 to res/raw
        //Musik von https://www.musicfox.com/info/kostenlose-gemafreie-musik.php

        counterMusic += insertMusic(1,"Floaters", 200, true, true, 2131558400)
        counterMusic += insertMusic(2,"Hall Of Mirrors", 200, false, true, 2131558401)
        counterMusic += insertMusic(3,"Jay Jay", 200, false, true, 2131558402)
        counterMusic += insertMusic(4,"Pas de Deux", 200, false, false, 2131558403)
        counterMusic += insertMusic(5,"Hooky with Sloane", 200, false, false, 2131558404)
        counterMusic += insertMusic(6,"Eine Kleine Nachtmusik (by Mozart)", 200, false, false, 2131558405)
        counterMusic += insertMusic(7,"Violet Spirit", 200, false, false, 2131558406)
        counterMusic += insertMusic(8,"Freedom?", 200, false, false, 2131558407)
        counterMusic += insertMusic(9,"From Russia With Love", 200, false, false, 2131558408)
        counterMusic += insertMusic(10,"Ambiente Pearls", 200, false, false, 2131558409)
        counterMusic += insertMusic(11,"Roadstar", 200, false, false, 2131558410)
        counterMusic += insertMusic(12,"Space Running", 200, false, false, 2131558411)
        counterMusic += insertMusic(13,"Pretty Organ", 200, false, false, 2131558412)
        counterMusic += insertMusic(14,"Sad Days", 200, false, false, 2131558413)
        counterMusic += insertMusic(15,"Its not easy", 200, false, false, 2131558414)
        counterMusic += insertMusic(16,"The small Farm", 200, false, false, 2131558415)
        counterMusic += insertMusic(17,"Loop Line", 200, false, false, 2131558416)
    }

    fun insertColor(id:Int, name:String, preis:Int, selected:Boolean, gekauft:Boolean, background:String, font:String):Int{
        val schreiber = this.writableDatabase
        val datensatz = ContentValues()
        datensatz.put("id", id)
        datensatz.put("name", name)
        datensatz.put("preis",preis)
        datensatz.put("selected", selected)
        datensatz.put("gekauft", gekauft)
        datensatz.put("background", background)
        datensatz.put("font", font)
        if(schreiber.insert(
                "color", null, datensatz) == -1L){
            schreiber.close()
            return 0
        }
        else{
            schreiber.close()
            return 1
        }
    }

    fun insertMusic(id:Int,name:String, preis:Int, selected:Boolean, gekauft:Boolean, source:Int):Int{
        val schreiber = this.writableDatabase
        val datensatz = ContentValues()
        datensatz.put("id", id)
        datensatz.put("name", name)
        datensatz.put("preis",preis)
        datensatz.put("selected", selected)
        datensatz.put("gekauft", gekauft)
        datensatz.put("source", source)
        if(schreiber.insert(
                "music", null, datensatz) == -1L){
            schreiber.close()
            return 0
        }
        else{
            schreiber.close()
            return 1
        }
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
