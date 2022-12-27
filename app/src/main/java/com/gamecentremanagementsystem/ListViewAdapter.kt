package com.gamecentremanagementsystem

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import java.text.SimpleDateFormat
import java.util.Calendar


class ListViewAdapter
    (
    private val context: Activity,
    private val time: ArrayList<Long>,
    private val console: ArrayList<String>,
    private val user: ArrayList<String>
) : ArrayAdapter<String>(context, R.layout.activity_listview, user)
{
    @SuppressLint("ViewHolder", "InflateParams", "SimpleDateFormat")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val sdf = SimpleDateFormat("hh:mm:ss")
        val calender = Calendar.getInstance()

        val inflater = context.layoutInflater
        val rowView: View = inflater.inflate(R.layout.activity_listview, null, true)
        val timeText = rowView.findViewById<View>(R.id.time) as TextView
        val consoleText = rowView.findViewById<View>(R.id.console) as TextView
        val userText = rowView.findViewById<View>(R.id.gamer) as TextView

        calender.timeInMillis = time[position]

        timeText.text = sdf.format(calender.time.time)
        consoleText.text = console[position]
        userText.text = user[position]

        return rowView
    }
}