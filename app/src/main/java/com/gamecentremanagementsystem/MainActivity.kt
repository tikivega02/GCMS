package com.gamecentremanagementsystem

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.System.currentTimeMillis
import java.text.SimpleDateFormat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DBHelper(this, null)
        val users = arrayListOf<String>()
        val consoles = arrayListOf<String>()
        val times = arrayListOf<Long>()

        val listview: ListView = findViewById(R.id.list_item)
        val arrayAdapter: ArrayAdapter<*> = ListViewAdapter(this,times,consoles, users)
        val cursor = db.getTimeRemaining()


        listview.isNestedScrollingEnabled = true;
        listview.startNestedScroll(View.OVER_SCROLL_ALWAYS);

        if (cursor != null && cursor.count>0) {
            while (cursor.moveToNext()) {
                val user  = cursor.getString(cursor.getColumnIndex(DBHelper.GAMER_NAME_COL));
                val console  = cursor.getString(cursor.getColumnIndex(DBHelper.CONSOLE_COL));
                val time  = cursor.getLong(cursor.getColumnIndex(DBHelper.TIME_END_COL));

                users.add(user)
                consoles.add(console)
                times.add(time)
            }
        }

        listview.adapter = arrayAdapter

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            startActivity(Intent(this,SetTimeUp::class.java))
            finish()
        }

//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AlarmFragment()).commit()
    }
}


