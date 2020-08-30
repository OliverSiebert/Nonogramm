package com.example.nonogramm_prototyp
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class StoryDatenbank(context:Context):
    SQLiteOpenHelper(context, "story.db", null, 1) {
    var counterLevel=0
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE [story]" +
                    "([id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "[name] TEXT NOT NULL, [selected] BOOLEAN NOT NULL, [A1] INTEGER NOT NULL, [B1] INTEGER NOT NULL, [C1] INTEGER NOT NULL, [D1] INTEGER NOT NULL, [E1] INTEGER NOT NULL, [A2] INTEGER NOT NULL, [B2] INTEGER NOT NULL, [C2] INTEGER NOT NULL, [D2] INTEGER NOT NULL, [E2] INTEGER NOT NULL, [A3] INTEGER NOT NULL, [B3] INTEGER NOT NULL, [C3] INTEGER NOT NULL, [D3] INTEGER NOT NULL, [E3] INTEGER NOT NULL, [A4] INTEGER NOT NULL, [B4] INTEGER NOT NULL, [C4] INTEGER NOT NULL, [D4] INTEGER NOT NULL, [E4] INTEGER NOT NULL, [A5] INTEGER NOT NULL, [B5] INTEGER NOT NULL, [C5] INTEGER NOT NULL, [D5] INTEGER NOT NULL, [E5] INTEGER NOT NULL, [solved] BOOLEAN NOT NULL)"
        )
    }

    fun initDatabase(){
        counterLevel += insertLevel(1, "A", false,0, 1, 1, 1, 0,1, 0, 0, 0, 1,1, 1, 1, 1, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,false)
        counterLevel += insertLevel(2,"B", false,1, 1, 1, 1, 0,1, 0, 0, 0, 1,1, 1, 1, 1, 0,1, 0, 0, 0, 1,1, 1, 1, 1, 0,false)
        counterLevel += insertLevel(3,"C", false, 1, 1, 1, 1, 0,1, 0, 0, 0, 0,1, 0, 0, 0, 0,1, 0, 0, 0, 0,1, 1, 1, 1, 0,false)
        counterLevel += insertLevel(4,"D", false,1, 1, 1, 1, 0,1, 0, 0, 0, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,1, 1, 1, 1, 0,false)
        counterLevel += insertLevel(5,"E", false,1, 1, 1, 1, 1,1, 0, 0, 0, 0,1, 1, 1, 0, 0,1, 0, 0, 0, 0,1, 1, 1, 1, 1,false)
        counterLevel += insertLevel(6,"F", false,1, 1, 1, 1, 1,1, 0, 0, 0, 0,1, 1, 1, 0, 0,1, 0, 0, 0, 0,1, 0, 0, 0, 0,false)
        counterLevel += insertLevel(7,"G", false,1, 1, 1, 1, 1,1, 0, 0, 0, 0,1, 0, 0, 1, 1,1, 0, 0, 0, 1,1, 1, 1, 1, 1,false)
        counterLevel += insertLevel(8,"H", false,1, 0, 0, 0, 1,1, 0, 0, 0, 1,1, 1, 1, 1, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,false)
        counterLevel += insertLevel(9,"I", false,0, 0, 1, 0, 0,0, 0, 1, 0, 0,0, 0, 1, 0, 0,0, 0, 1, 0, 0,0, 0, 1, 0, 0,false)
        counterLevel += insertLevel(10,"J", false,0, 1, 1, 1, 0,0, 0, 0, 1, 0,0, 0, 0, 1, 0,0, 1, 0, 1, 0,0, 1, 1, 1, 0,false)
        counterLevel += insertLevel(11,"K", false,1, 0, 0, 1, 0,1, 0, 1, 0, 0,1, 1, 0, 0, 0,1, 0, 1, 0, 0,1, 0, 0, 1, 0,false)
        counterLevel += insertLevel(12,"L", false,1, 0, 0, 0, 0,1, 0, 0, 0, 0,1, 0, 0, 0, 0,1, 0, 0, 0, 0,1, 1, 1, 1, 0,false)
        counterLevel += insertLevel(13,"M", false,1, 0, 0, 0, 1,1, 1, 0, 1, 1,1, 0, 1, 0, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,false)
        counterLevel += insertLevel(14,"N", false,1, 0, 0, 0, 1,1, 1, 0, 0, 1,1, 0, 1, 0, 1,1, 0, 0, 1, 1,1, 0, 0, 0, 1,false)
        counterLevel += insertLevel(15,"O", false,1, 1, 1, 1, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,1, 1, 1, 1, 1,false)
        counterLevel += insertLevel(16,"P", false,1, 1, 1, 1, 0,1, 0, 0, 0, 1,1, 1, 1, 1, 0,1, 0, 0, 0, 0,1, 0, 0, 0, 0,false)
        counterLevel += insertLevel(17,"Q", false,1, 1, 1, 1, 1,1, 0, 0, 0, 1,1, 0, 0, 1, 1,1, 1, 1, 1, 1,0, 0, 0, 0, 1,false)
        counterLevel += insertLevel(18,"R", false,1, 1, 1, 1, 0,1, 0, 0, 0, 1,1, 1, 1, 1, 0,1, 0, 1, 0, 0,1, 0, 0, 1, 0,false)
        counterLevel += insertLevel(19,"S", false,1, 1, 1, 1, 0,1, 0, 0, 0, 0,1, 1, 1, 1, 1,0, 0, 0, 0, 1,1, 1, 1, 1, 1,false)
        counterLevel += insertLevel(20,"T", false, 1, 1, 1, 1, 1,0, 0, 1, 0, 0,0, 0, 1, 0, 0,0, 0, 1, 0, 0,0, 0, 1, 0, 0,false)
        counterLevel += insertLevel(21,"U", false, 1, 0, 0, 0, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,0, 1, 1, 1, 0,false)
        counterLevel += insertLevel(22,"V", false, 1, 0, 0, 0, 1,1, 0, 0, 0, 1,1, 0, 0, 0, 1,0, 1, 0, 1, 0,0, 0, 1, 0, 0,false)
        counterLevel += insertLevel(23,"W", false, 1, 0, 0, 0, 1,1, 0, 1, 0, 1,1, 0, 1, 0, 1,1, 0, 1, 0, 1,0, 1, 0, 1, 0,false)
        counterLevel += insertLevel(24,"X", false, 1, 0, 0, 0, 1,0, 1, 0, 1, 0,0, 0, 1, 0, 0,0, 1, 0, 1, 0,1, 0, 0, 0, 1,false)
        counterLevel += insertLevel(25,"Y", false, 1, 0, 0, 0, 1,1, 0, 0, 0, 1,0, 1, 0, 1, 0,0, 0, 1, 0, 0,0, 0, 1, 0, 0,false)
        counterLevel += insertLevel(26,"Z", false, 1, 1, 1, 1, 1,0, 0, 0, 1, 0,0, 0, 1, 0, 0,0, 1, 0, 0, 0,1, 1, 1, 1, 1,false)

    }

    private fun insertLevel(id:Int, name:String, selected:Boolean, a1:Int, b1:Int, c1:Int, d1:Int, e1:Int, a2:Int, b2:Int, c2:Int, d2:Int, e2:Int, a3:Int, b3:Int, c3:Int, d3:Int, e3:Int, a4:Int, b4:Int, c4:Int, d4:Int, e4:Int, a5:Int, b5:Int, c5:Int, d5:Int, e5:Int, solved:Boolean): Int {
        val schreiber = this.writableDatabase
        val datensatz = ContentValues()
        datensatz.put("id", id)
        datensatz.put("name", name)
        datensatz.put("selected", selected)

        datensatz.put("A1", a1)
        datensatz.put("B1", b1)
        datensatz.put("C1", c1)
        datensatz.put("D1", d1)
        datensatz.put("E1", e1)

        datensatz.put("A2", a2)
        datensatz.put("B2", b2)
        datensatz.put("C2", c2)
        datensatz.put("D2", d2)
        datensatz.put("E2", e2)

        datensatz.put("A3", a3)
        datensatz.put("B3", b3)
        datensatz.put("C3", c3)
        datensatz.put("D3", d3)
        datensatz.put("E3", e3)

        datensatz.put("A4", a4)
        datensatz.put("B4", b4)
        datensatz.put("C4", c4)
        datensatz.put("D4", d4)
        datensatz.put("E4", e4)

        datensatz.put("A5", a5)
        datensatz.put("B5", b5)
        datensatz.put("C5", c5)
        datensatz.put("D5", d5)
        datensatz.put("E5", e5)
        datensatz.put("solved", solved)
        if(schreiber.insert(
                "story", null, datensatz) == -1L){
            schreiber.close()
            return 0
        }
        else{
            schreiber.close()
            return 1
        }
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        versionAlt: Int, versionNeu: Int
    ) {

    }
}