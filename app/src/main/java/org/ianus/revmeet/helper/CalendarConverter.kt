package org.ianus.revmeet.helper

import java.text.SimpleDateFormat
import java.util.Calendar


object CalendarConverter {

    @JvmStatic
    fun main(args: Array<String>) {
        println(getDate(82233213123L, "dd/MM/yyyy hh:mm:ss.SSS"))
    }

    fun getDate(milliSeconds: Long, dateFormat: String): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(milliSeconds)
        return formatter.format(calendar.getTime())
    }
}