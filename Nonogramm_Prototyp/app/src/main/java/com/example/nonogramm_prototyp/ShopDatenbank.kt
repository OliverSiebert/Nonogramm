package com.example.nonogramm_prototyp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ShopDatenbank(context:Context):
    SQLiteOpenHelper(context, "color.db", null, 1){
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

        // DB COLOR  Values
        //change colorMax in Shop.kt
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (1, 'White', 100, true, true, '#FFFFFF', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (2, 'Red', 100, false, true, '#FF0000', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (3, 'Orange', 100, false, true, '#FFA500', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (4, 'Yellow', 100, false, true, '#FFFF00', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (5, 'Green', 100, false, true, '#00FF00', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (6, 'Blue', 100, false, true, '#0000FF', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (7, 'Purple', 100, false, true, '#A020F0', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (8, 'Gray', 100, false, true, '#BEBEBE', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (9, 'CadetBlue', 100, false, false, '#5F9EA0', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (10, 'DeepSkyBlue', 100, false, false, '#00BFFF', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (11, 'DodgerBlue', 100, false, false, '#1E90FF', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (12, 'LightBlue', 100, false, false, '#ADD8E6', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (13, 'LightCyan', 100, false, false, '#E0FFFF', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (14, 'LightSkyBlue', 100, false, false, '#87CEFA', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (15, 'LightSteelBlue', 100, false, false, '#B0C4DE', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (16, 'PaleTurquoise', 100, false, false, '#AFEEEE', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (17, 'RoyalBlue', 100, false, false, '#4169E1', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (18, 'SkyBlue', 100, false, false, '#87CEEB', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (19, 'SlateBlue', 100, false, false, '#6A5ACD', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (20, 'SteelBlue', 100, false, false, '#4682B4', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (21, 'Aquamarine', 100, false, false, '#7FFFD4', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (22, 'Cyan', 100, false, false, '#00FFFF', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (23, 'Turquoise', 100, false, false, '#40E0D0', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (24, 'Navy', 100, false, false, '#000080', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (25, 'RosyBrown', 100, false, false, '#BC8F8F', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (26, 'SandyBrown', 100, false, false, '#F4A460', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (27, 'Beige', 100, false, false, '#F5F5DC', '#000000')"
                )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (28, 'Brown', 100, false, false, '#A52A2A', '#FFFFFF')"
                )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (29, 'Burlywood', 100, false, false, '#DEB887', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (30, 'Chocolate', 100, false, false, '#D2691E', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (31, 'Peru', 100, false, false, '#CD853F', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (32, 'DarkSlateGray', 100, false, false, '#2F4F4F', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (33, 'LightSlateGray', 100, false, false, '#778899', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (34, 'SlateGray', 100, false, false, '#708090', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (35, 'Khaki', 100, false, false, '#F0E68C', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (36, 'OliveDrab', 100, false, false, '#6B8E23', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (37, 'DarkGreen', 100, false, false, '#006400', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (38, 'ForestGreen', 100, false, false, '#228B22', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (39, 'SeaGreen', 100, false, false, '#2E8B57', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (40, 'Coral', 100, false, false, '#FF7F50', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (41, 'Salmon', 100, false, false, '#FA8072', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (42, 'Bisque', 100, false, false, '#FFE4C4', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (43, 'Sienna', 100, false, false, '#A0522D', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (44, 'DarkRed', 100, false, false, '#8B0000', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (45, 'DeepPink', 100, false, false, '#FF1493', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (46, 'HotPink', 100, false, false, '#FF69B4', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (47, 'VioletRed', 100, false, false, '#D02090', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (48, 'Firebrick', 100, false, false, '#B22222', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (49, 'Tomato', 100, false, false, '#FF6347', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (50, 'DarkMagenta', 100, false, false, '#8B008B', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (51, 'Violet', 100, false, false, '#EE82EE', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (52, 'Magenta', 100, false, false, '#FF00FF', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (53, 'Maroon', 100, false, false, '#B03060', '#FFFFFF')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (54, 'Orchid', 100, false, false, '#DA70D6', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (55, 'Plum', 100, false, false, '#DDA0DD', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (56, 'NavajoWhite', 100, false, false, '#FFDEAD', '#000000')"
        )
        db.execSQL(
            "INSERT INTO [color]" +
                    "VALUES (57, 'Gold', 100, false, false, '#FFD700', '#000000')"
        )


        // DB MUSIC
        //change musicMax in Shop.kt
        // add mp3 to res/raw
        //Musik von https://www.musicfox.com/info/kostenlose-gemafreie-musik.php
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (1, 'Floaters', 200, true, true, 2131558400)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (2, 'Hall Of Mirrors', 200, false, true, 2131558401)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (3, 'Jay Jay', 200, false, true, 2131558402)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (4, 'Pas de Deux', 200, false, false, 2131558403)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (5, 'Hooky with Sloane', 200, false, false, 2131558404)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (6, 'Eine Kleine Nachtmusik (by Mozart)', 200, false, false, 2131558405)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (7, 'Violet Spirit', 200, false, false, 2131558406)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (8, 'Freedom?', 200, false, false, 2131558407)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (9, 'From Russia With Love', 200, false, false, 2131558408)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (10, 'Ambiente Pearls', 200, false, false, 2131558409)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (11, 'Roadstar', 200, false, false, 2131558410)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (12, 'Space Running', 200, false, false, 2131558411)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (13, 'Pretty Organ', 200, false, false, 2131558412)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (14, 'Sad Days', 200, false, false, 2131558413)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (15, 'Its not easy', 200, false, false, 2131558414)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (16, 'The small Farm', 200, false, false, 2131558415)"
        )
        db.execSQL(
            "INSERT INTO [music]" +
                    "VALUES (17, 'Loop Line', 200, false, false, 2131558416)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
