package com.example.academy_intern.ment2link.activities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.academy_intern.ment2link.R

import android.support.design.widget.FloatingActionButton
import android.view.View

import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import java.util.ArrayList
import java.util.Calendar

import com.example.academy_intern.ment2link.pojos.MyEventDay

///name of class
class CalenderActivity : AppCompatActivity() {
    private var mCalendarView: CalendarView? = null
    private val mEventDays = ArrayList<EventDay>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calender)

        ///viewing the calendar and clicking the floating button
        mCalendarView = findViewById<View>(R.id.calendarView) as CalendarView
        val floatingActionButton = findViewById<View>(R.id.floatingActionButton) as FloatingActionButton
        floatingActionButton.setOnClickListener(View.OnClickListener { addNote() })
    }

    ///Viewing Event
    fun MyEventDay(view: View) {
        val MyEventDay = Calendar.getInstance()
        val i = Intent(Intent.ACTION_EDIT)
        i.type = "vnd.android.cursor.item/event"
        i.putExtra("beginTime", MyEventDay.timeInMillis)
        i.putExtra("allDay", true)
        i.putExtra("rule", "FREQ=YEARLY")
        i.putExtra("endTime", MyEventDay.timeInMillis + 60 * 60 * 1000)
        i.putExtra("title", "Sample Calender Event Android Application")
        startActivity(i)

        ///Clicking On The Day To See Your Event
        mCalendarView!!.setOnDayClickListener(object : OnDayClickListener {
            override fun onDayClick(eventDay: EventDay) {}
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == ADD_NOTE && resultCode == Activity.RESULT_OK) {
            val myEventDay = data.getParcelableExtra<MyEventDay>(RESULT)
            mCalendarView!!.setDate(myEventDay.getCalendar())
            mEventDays.add(myEventDay)
            mCalendarView!!.setEvents(mEventDays)
        }
    }

    ///Adding Event
    private fun addNote() {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivityForResult(intent, ADD_NOTE)
    }

    ///Previewing The Event
    private fun previewNote(eventDay: EventDay) {
        val intent = Intent(this, NotePreviewActivity::class.java)
        if (eventDay is MyEventDay) {
            intent.putExtra(EVENT, eventDay as MyEventDay)
        }
        startActivity(intent)
    }

    companion object {

        val RESULT = "result"
        val EVENT = "event"
        private val ADD_NOTE = 44
    }
}
