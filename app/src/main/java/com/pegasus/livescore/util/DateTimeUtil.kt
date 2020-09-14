package com.pegasus.livescore.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton


object DateTimeUtil {
    @Singleton
    fun stringToDateConverter(datestring: String?) : Calendar? {
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)
        try {
            var calendar = Calendar.getInstance()
            calendar.time  = format.parse(datestring)
            return calendar
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }
}