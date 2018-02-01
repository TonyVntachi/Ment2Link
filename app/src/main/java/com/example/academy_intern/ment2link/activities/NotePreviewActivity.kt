package com.example.academy_intern.ment2link.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import com.applandeo.materialcalendarview.EventDay
import com.example.academy_intern.ment2link.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.academy_intern.ment2link.pojos.MyEventDay

///class
class NotePreviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_preview)

        ///Getting The Info On Input By Adding Event
        val intent = intent
        val note = findViewById<View>(R.id.note) as TextView
        if (intent != null) {
            val event = intent.getParcelableExtra<Parcelable>(CalenderActivity.EVENT)
            if (event is MyEventDay) {
                val myEventDay = event
                supportActionBar!!.title = getFormattedDate(myEventDay.calendar.time)
                note.text = myEventDay.note
                return
            }
            if (event is EventDay) {
                val eventDay = event as EventDay
                supportActionBar!!.title = getFormattedDate(eventDay.calendar.time)
            }
        }
    }

    ///On Date
    companion object {
        fun getFormattedDate(date: Date): String {
            val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            return simpleDateFormat.format(date)
        }
    }
}

