package com.android.dailynote.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TimeClass {

    private val timeZone: TimeZone = TimeZone.getTimeZone("Asia/Seoul")

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTimeToString(cal : Calendar,dateType: DateType,recentMonth: Int = 0): String {
        val ymdFormatter = "yyyy/MM/dd"
        val hmsFormatter = "HH:mm:ss"
        val ymdFormat = SimpleDateFormat(ymdFormatter)
        val dateFormat = SimpleDateFormat("$ymdFormatter $hmsFormatter")

        cal.timeZone = timeZone

        return when (dateType) {
            DateType.TO_DATE -> {
                cal.add(Calendar.MONTH, -recentMonth)
                ymdFormat.format(cal.time)
            }
            DateType.FROM_DATE -> ymdFormat.format(cal.time)
            DateType.NOW -> dateFormat.format(cal.time)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTimeToDate(cal : Calendar, dateType: DateType, recentMonth: Int = 0): Date {
        cal.timeZone = timeZone

        return when (dateType) {
            DateType.TO_DATE -> {
                cal.add(Calendar.MONTH, -recentMonth)
                cal.set(Calendar.HOUR_OF_DAY,0)
                cal.set(Calendar.MINUTE,0)
                cal.set(Calendar.SECOND,0)
                cal.time
            }
            DateType.FROM_DATE -> {
                cal.set(Calendar.HOUR_OF_DAY, 23)
                cal.set(Calendar.MINUTE,59)
                cal.set(Calendar.SECOND,59)
                cal.time
            }
            DateType.NOW -> cal.time
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun stringToDate (dateString : String,format:String) :Date = SimpleDateFormat(format).parse(dateString)!!

    @SuppressLint("SimpleDateFormat")
    fun dateToString (date : Date, format: String) :String  = SimpleDateFormat(format).format(date)

}