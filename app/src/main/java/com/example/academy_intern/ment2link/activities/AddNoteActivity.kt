package com.example.academy_intern.ment2link.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.applandeo.materialcalendarview.CalendarView
import com.example.academy_intern.ment2link.R

import com.example.academy_intern.ment2link.pojos.MyEventDay

///class
class AddNoteActivity : AppCompatActivity() {

    ///OnCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)



        val datePicker = findViewById<View>(R.id.datePicker) as com.applandeo.materialcalendarview.CalendarView


        /// Button To Add Event
        val button = findViewById<View>(R.id.addNoteButton) as android.support.v7.widget.AppCompatButton
        val noteEditText = findViewById<View>(R.id.noteEditText) as EditText
        button.setOnClickListener {
            val returnIntent = Intent()


            ///Notification To See That Theres An Event On That Day
            val myEventDay = MyEventDay(datePicker.getSelectedDate(),
                    R.drawable.ic_message_black_48dp, noteEditText.text.toString())
            returnIntent.putExtra(CalenderActivity.RESULT, myEventDay)
            setResult(Activity.RESULT_OK, returnIntent)
            //startActivityForResult()
            finish()
        }
    }
}
