package com.example.academy_intern.ment2link.pojos

/**
 * Created by academy_intern on 1/26/18.
 */

import android.os.Parcel
import android.os.Parcelable

import com.applandeo.materialcalendarview.EventDay

import java.util.Calendar

internal class MyEventDay : EventDay, Parcelable {
    var note: String? = null
        private set

    constructor(day: Calendar, imageResource: Int, note: String) : super(day, imageResource) {
        this.note = note
    }

    private constructor(`in`: Parcel) : super(`in`.readSerializable() as Calendar, `in`.readInt()) {
        note = `in`.readString()
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeSerializable(getCalendar())
        parcel.writeInt(getImageResource())
        parcel.writeString(note)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {


        val CREATOR: Parcelable.Creator<MyEventDay> = object : Parcelable.Creator<MyEventDay> {
            override fun createFromParcel(`in`: Parcel): MyEventDay {
                return MyEventDay(`in`)
            }

            override fun newArray(size: Int): Array<MyEventDay?> {
                return arrayOfNulls(size)
            }
        }
    }
}