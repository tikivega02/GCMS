package com.gamecentremanagementsystem

import android.content.Intent
import android.graphics.PixelFormat
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button

class TimeUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("State", "onCreate: Created")
        setContentView(R.layout.activity_time_up)

       /* window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or PixelFormat.TRANSLUCENT)*/

        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val tone = RingtoneManager.getRingtone(this, uri)
        tone.play()

        findViewById<Button>(R.id.stop_alarm).setOnClickListener {
            tone.stop();
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d("State", "onStop: Stopped")
    }

    override fun onPause() {
        super.onPause()
        Log.d("State", "onPause: Paused")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("State", "onDestroy: Destroyed")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("State", "onRestart: Restarted")
    }
}