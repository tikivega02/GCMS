package com.gamecentremanagementsystem

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class SetTimeUp : AppCompatActivity() {
   @SuppressLint("MissingInflatedId", "CutPasteId", "ResourceType", "SetTextI18n",
        "SimpleDateFormat"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_time_up)
//        findViewById<TimePicker>(R.id.time_picker).hour = 0
//        findViewById<TimePicker>(R.id.time_picker).minute = 1
        findViewById<TimePicker>(R.id.time_picker).currentHour = 0
        findViewById<TimePicker>(R.id.time_picker).currentMinute = 30
        findViewById<TimePicker>(R.id.time_picker).setIs24HourView(true)
        val db = DBHelper(this, null);

        val radioGroup = RadioGroup(this)
        val radioGroupUser = RadioGroup(this)
        val layoutParams : RadioGroup.LayoutParams = RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,RadioGroup.LayoutParams.MATCH_PARENT)
        radioGroup.orientation = LinearLayout.VERTICAL
        radioGroup.layoutDirection = View.LAYOUT_DIRECTION_RTL
        radioGroup.showDividers = RadioGroup.SHOW_DIVIDER_MIDDLE

        radioGroupUser.orientation = LinearLayout.VERTICAL
        radioGroupUser.layoutDirection = View.LAYOUT_DIRECTION_RTL
        radioGroupUser.showDividers = RadioGroup.SHOW_DIVIDER_MIDDLE

        val console = arrayOf("XBox One 1","XBox One 2","XBox One 3","XBox One 4","XBox One 5", "XBox One 6", "XBox One 7", "XBox One X - 1" , "XBox One X - 2","PS5 - 1", "PS5 - 2")
        val user = arrayOf("Tiki","Vega","Niki","View", "DKG")

        for (i in console)
        {
            val radioButton = RadioButton(this)
            radioButton.text = i
            radioGroup.addView(radioButton, layoutParams)
        }

        for (i in user)
        {
            val radioButton = RadioButton(this)
            radioButton.text = i
            radioGroupUser.addView(radioButton, layoutParams)
        }

        findViewById<LinearLayout>(R.id.console).setOnClickListener{
            val consoleBottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            val view : View = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet, findViewById<LinearLayout>(R.id.bottomSheetContainer))
            view.findViewById<TextView>(R.id.bottom_sheet_textview).text = "Consoles"
            view.findViewById<LinearLayout>(R.id.bottomSheetContainer).addView(radioGroup)
            consoleBottomSheetDialog.setContentView(view)
            consoleBottomSheetDialog.show()

            radioGroup.setOnCheckedChangeListener{
                    _:RadioGroup, checkedRadioId: Int ->
                val checkedRadio = radioGroup.findViewById<RadioButton>(checkedRadioId)
                findViewById<TextView>(R.id.console_textview).text = checkedRadio.text
                Toast.makeText(this, checkedRadio.text, Toast.LENGTH_LONG).show()
                consoleBottomSheetDialog.dismiss()
            }
        }

        findViewById<LinearLayout>(R.id.user).setOnClickListener{
            val consoleBottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            val view : View = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet, findViewById<LinearLayout>(R.id.bottomSheetContainer))
            view.findViewById<TextView>(R.id.bottom_sheet_textview).text = "Users"
            view.findViewById<LinearLayout>(R.id.bottomSheetContainer).addView(radioGroupUser)
            consoleBottomSheetDialog.setContentView(view)
            consoleBottomSheetDialog.show()

            radioGroupUser.setOnCheckedChangeListener{
                    _:RadioGroup, checkedRadioId: Int ->
                val checkedRadio = radioGroupUser.findViewById<RadioButton>(checkedRadioId)
                findViewById<TextView>(R.id.user_textview).text = checkedRadio.text
                Toast.makeText(this, checkedRadio.text, Toast.LENGTH_LONG).show()
                consoleBottomSheetDialog.dismiss()
            }
        }

        findViewById<LinearLayout>(R.id.time_giver).setOnClickListener{
            val consoleBottomSheetDialog = BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
            val view : View = LayoutInflater.from(this).inflate(R.layout.layout_bottom_sheet, findViewById<LinearLayout>(R.id.bottomSheetContainer))
            view.findViewById<TextView>(R.id.bottom_sheet_textview).text = "Enter Your Name"
            view.findViewById<EditText>(R.id.edit_text).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.edit_text_linearlayout).visibility = View.VISIBLE
            consoleBottomSheetDialog.setContentView(view)
            consoleBottomSheetDialog.show()

            view.findViewById<Button>(R.id.buttom_ok).setOnClickListener{
                val edittext =  view.findViewById<EditText>(R.id.edit_text).text
                findViewById<TextView>(R.id.timer_textview).text = edittext
                Toast.makeText(this, edittext, Toast.LENGTH_LONG).show()

                consoleBottomSheetDialog.dismiss()
            }

            view.findViewById<Button>(R.id.buttom_cancel).setOnClickListener{
                consoleBottomSheetDialog.dismiss()
            }
        }

        findViewById<Button>(R.id.submit).setOnClickListener{
            val timerPicker = findViewById<TimePicker>(R.id.time_picker)
            val calHour = (60 * timerPicker.currentHour)
            val console = findViewById<TextView>(R.id.console_textview).text.toString()
            val user = findViewById<TextView>(R.id.user_textview).text.toString()
            val timeStart = System.currentTimeMillis()
            val timeToEnd = System.currentTimeMillis() + (calHour + timerPicker.currentMinute) * 60000L
            val timer = findViewById<TextView>(R.id.timer_textview).text.toString()

            db.addRecord(console, user, timeToEnd, false, timeStart,timeToEnd, timer)

            startAlarm(timeToEnd,console)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }

    @SuppressLint("SimpleDateFormat", "UnspecifiedImmutableFlag")
    private fun startAlarm(calTime: Long, name:String)
    {
        val cal = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("hh:mm:ss")
        val testTime = System.currentTimeMillis() + 10 * 1000L

        val intent = Intent(this, TimeUpActivity::class.java)
        intent.putExtra("name", name);
        val id = System.currentTimeMillis().toInt()
        val pendingIntent = PendingIntent.getActivity(
            this, id, intent, FLAG_ONE_SHOT
        )

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

//        cal.timeInMillis = System.currentTimeMillis()
//        Log.d("current", cal.time.toString())
        cal.timeInMillis = calTime
//        Log.d("addeddd", cal.time.toString())

//        alarmManager.setAlarmClock(alarmClockInfo, pendingIntent)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calTime, pendingIntent)
        Toast.makeText(this, "Alarm set in ${timeFormat.format(cal.time.time)}", Toast.LENGTH_LONG).show()
    }

    private fun stopAlarm()
    {
        val intent = Intent(this, TimeUpActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, 0
        )

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}