package com.gamecentremanagementsystem

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.PixelFormat
import android.media.RingtoneManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TimeUpActivity : AppCompatActivity() {
     private var username: String = ""


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_up)
        window.addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
        )

        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val tone = RingtoneManager.getRingtone(this, uri)
        val extras = intent.extras

        if (extras != null) {
            username = extras.getString("name").toString()
        }
        tone.play()

        findViewById<TextView>(R.id.name).text = username

        findViewById<Button>(R.id.stop_alarm).setOnClickListener {
            tone.stop()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}