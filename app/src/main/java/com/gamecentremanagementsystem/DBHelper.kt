package com.gamecentremanagementsystem


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE $TABLE_NAME  ($ID_COL INTEGER PRIMARY KEY, " +
                "$CONSOLE_COL  TEXT,$GAMER_NAME_COL TEXT," +
                "$TIME_REM_COL  LONG,$PAUSED_COL BOOLEAN," +
                "$TIME_STARTED_COL LONG,$TIME_END_COL LONG," +
                "$TIMER_NAME_COL  TEXT)")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addRecord(console:String,gamer:String,time_rem:Long,paused:Boolean, time_started:Long,time_to_end:Long, timer:String){
        val values = ContentValues()
        values.put(CONSOLE_COL, console)
        values.put(GAMER_NAME_COL, gamer)
        values.put(TIME_REM_COL, time_rem)
        values.put(PAUSED_COL, paused)
        values.put(TIME_STARTED_COL, time_started)
        values.put(TIME_END_COL, time_to_end)
        values.put(TIMER_NAME_COL, timer)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        //db.close()
    }

    fun getTimeRemaining(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME where $TIME_END_COL >= ${System.currentTimeMillis()}", null)
    }

    fun deleteName(id:String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"ID=?", Array(1) { id })
    }

    companion object{
        // below is variable for database name
        private const val DATABASE_NAME = "DB_LOVE"

        // below is the variable for database version
        private const val DATABASE_VERSION = 1

        // below is the variable for table name
        // [recordTest1]
        const val TABLE_NAME = "recordTest2"



        // below is the variable for id column
        const val ID_COL = "ID"

        const val CONSOLE_COL = "Console"

        const val GAMER_NAME_COL = "GamerName"

        const val TIME_REM_COL = "TimeRemaining"

        const val PAUSED_COL = "Pause"

        const val TIME_STARTED_COL = "TimeStarted"

        const val TIME_END_COL = "TimeToEnd"

        const val TIMER_NAME_COL = "TimerName"
    }
}