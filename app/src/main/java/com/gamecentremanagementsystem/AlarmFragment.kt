package com.gamecentremanagementsystem

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AlarmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlarmFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

//        val db = DBHelper(this, null)
//        val users = arrayListOf<String>()
//        val consoles = arrayListOf<String>()
//        val times = arrayListOf<Long>()
//
//        val listview: ListView = findViewById(R.id.list_item)
//        val arrayAdapter: ArrayAdapter<*> = ListViewAdapter(this,times,consoles, users)
//        val cursor = db.getTimeRemaining()
//
//
//        listview.isNestedScrollingEnabled = true;
//        listview.startNestedScroll(View.OVER_SCROLL_ALWAYS);
//
//        if (cursor != null && cursor.count>0) {
//            while (cursor.moveToNext()) {
//                val user  = cursor.getString(cursor.getColumnIndex(DBHelper.GAMER_NAME_COL));
//                val console  = cursor.getString(cursor.getColumnIndex(DBHelper.CONSOLE_COL));
//                val time  = cursor.getLong(cursor.getColumnIndex(DBHelper.TIME_END_COL));
//
//                users.add(user)
//                consoles.add(console)
//                times.add(time)
//            }
//        }

//        listview.adapter = arrayAdapter

//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
//            startActivity(Intent(this,SetTimeUp::class.java))
//            finish()
//        }
    }

    private fun finish() {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AlarmFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlarmFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}