package com.pegasus.livescore.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton


object DateTimeUtil {
    @Singleton
    fun stringToDateConverter(datestring: String?) : Date? {
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)
        try {
            return format.parse(datestring)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }
}