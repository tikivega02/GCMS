package com.gamecentremanagementsystem

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

     private val users = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
           TimePickerDialog(this,this, 0,30,true).show()

        }
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val listview: ListView = findViewById(R.id.list_item)

        users.add("$p2 seconds")
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter(this,android.R.layout.simple_list_item_1,users)

        listview.adapter = arrayAdapter

        startAlarm(p2)

    }

    private fun startAlarm(i: Int)
    {

        val intent = Intent(this, TimeUp::class.java)

        val pendingIntent = PendingIntent.getActivity(
            this, 234324243, intent, 0
        )

        val alarmClockInfo = AlarmManager.AlarmClockInfo(
            System.currentTimeMillis()
                    + i * 1000L, pendingIntent
        )

        intent.putExtra("user", 8)

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
        Toast.makeText(this, "Alarm set in $i seconds", Toast.LENGTH_LONG).show()

//        startActivity(intent)
    }
}


