package com.example.nonogramm_prototyp
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class StoryDatenbank(context:Context):
    SQLiteOpenHelper(context, "story.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE [story]" +
                    "([id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "[name] TEXT NOT NULL, [selected] BOOLEAN NOT NULL, [A1] INTEGER NOT NULL, [B1] INTEGER NOT NULL, [C1] INTEGER NOT NULL, [D1] INTEGER NOT NULL, [E1] INTEGER NOT NULL, [A2] INTEGER NOT NULL, [B2] INTEGER NOT NULL, [C2] INTEGER NOT NULL, [D2] INTEGER NOT NULL, [E2] INTEGER NOT NULL, [A3] INTEGER NOT NULL, [B3] INTEGER NOT NULL, [C3] INTEGER NOT NULL, [D3] INTEGER NOT NULL, [E3] INTEGER NOT NULL, [A4] INTEGER NOT NULL, [B4] INTEGER NOT NULL, [C4] INTEGER NOT NULL, [D4] INTEGER NOT NULL, [E4] INTEGER NOT NULL, [A5] INTEGER NOT NULL, [B5] INTEGER NOT NULL, [C5] INTEGER NOT NULL, [D5] INTEGER NOT NULL, [E5] INTEGER NOT NULL, [solved] BOOLEAN NOT NULL)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (1, 'A', false," +
                    "0, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (2,'B', false, " +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (3,'C', false, " +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 1, 1, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (4,'D', false, " +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (5,'E', false, " +
                    "1, 1, 1, 1, 1," +
                    "1, 0, 0, 0, 0," +
                    "1, 1, 1, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 1, 1, 1, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (6,'F', false, " +
                    "1, 1, 1, 1, 1," +
                    "1, 0, 0, 0, 0," +
                    "1, 1, 1, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (7,'G', false, " +
                    "1, 1, 1, 1, 1," +
                    "1, 0, 0, 0, 0," +
                    "1, 0, 0, 1, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (8,'H', false, " +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (9,'I', false, " +
                    "0, 0, 1, 0, 0," +
                    "0, 0, 1, 0, 0," +
                    "0, 0, 1, 0, 0," +
                    "0, 0, 1, 0, 0," +
                    "0, 0, 1, 0, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (10,'J', false, " +
                    "0, 1, 1, 1, 0," +
                    "0, 0, 0, 1, 0," +
                    "0, 0, 0, 1, 0," +
                    "0, 1, 0, 1, 0," +
                    "0, 1, 1, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (11,'K', false, " +
                    "1, 0, 0, 1, 0," +
                    "1, 0, 1, 0, 0," +
                    "1, 1, 0, 0, 0," +
                    "1, 0, 1, 0, 0," +
                    "1, 0, 0, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (12,'L', false, " +
                    "1, 0, 0, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 1, 1, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (13,'M', false, " +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 0, 1, 1," +
                    "1, 0, 1, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (14,'N', false, " +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 0, 0, 1," +
                    "1, 0, 1, 0, 1," +
                    "1, 0, 0, 1, 1," +
                    "1, 0, 0, 0, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (15,'O', false, " +
                    "1, 1, 1, 1, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (16,'P', false, " +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 0, 0, 0, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (17,'Q', false, " +
                    "1, 1, 1, 1, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 1, 1," +
                    "1, 1, 1, 1, 1," +
                    "0, 0, 0, 0, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (18,'R', false, " +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 1, 0, 0," +
                    "1, 0, 0, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (19,'S', false, " +
                    "1, 1, 1, 1, 0," +
                    "1, 0, 0, 0, 0," +
                    "1, 1, 1, 1, 1," +
                    "0, 0, 0, 0, 1," +
                    "1, 1, 1, 1, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (20,'T', false, " +
                    "1, 1, 1, 1, 1," +
                    "0, 0, 1, 0, 0," +
                    "0, 0, 1, 0, 0," +
                    "0, 0, 1, 0, 0," +
                    "0, 0, 1, 0, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (21,'U', false, " +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "0, 1, 1, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (22,'V', false, " +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "0, 1, 0, 1, 0," +
                    "0, 0, 1, 0, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (23,'W', false, " +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 1, 0, 1," +
                    "1, 0, 1, 0, 1," +
                    "1, 0, 1, 0, 1," +
                    "0, 1, 0, 1, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (24,'X', false, " +
                    "1, 0, 0, 0, 1," +
                    "0, 1, 0, 1, 0," +
                    "0, 0, 1, 0, 0," +
                    "0, 1, 0, 1, 0," +
                    "1, 0, 0, 0, 1," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (25,'Y', false, " +
                    "1, 0, 0, 0, 1," +
                    "1, 0, 0, 0, 1," +
                    "0, 1, 0, 1, 0," +
                    "0, 0, 1, 0, 0," +
                    "0, 0, 1, 0, 0," +
                    "false)"
        )
        db.execSQL(
            "INSERT INTO [story]" +
                    "VALUES (26,'Z', false, " +
                    "1, 1, 1, 1, 1," +
                    "0, 0, 0, 1, 0," +
                    "0, 0, 1, 0, 0," +
                    "0, 1, 0, 0, 0," +
                    "1, 1, 1, 1, 1," +
                    "false)"
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        versionAlt: Int, versionNeu: Int
    ) {

    }
}