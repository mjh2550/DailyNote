package com.android.dailynote.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class TimeClass {

    @SuppressLint("SimpleDateFormat")
    fun getCurrentTimeStringByDateType(dateType:DateType) : String {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]
        val mHour = c[Calendar.HOUR]
        val mMinute = c[Calendar.MINUTE]
        val mSecond = c[Calendar.SECOND]
        val recentMonth = 1
        val day = "$mYear/$mMonth/$mDay"
        val time = "$mHour:$mMinute:$mSecond"
        val toDate = "$mYear/${if(mMonth-recentMonth<=0) kotlin.math.abs(mMonth - recentMonth)+1 else {mMonth-recentMonth}}/$mDay"
        val fromDate =  "$mYear/$mMonth/$mDay"
        val now = "$day $time"
        return when (dateType){
            DateType.TO_DATE -> toDate
            DateType.FROM_DATE -> fromDate
            DateType.NOW -> now
        }
    }
    @SuppressLint("SimpleDateFormat")
    fun getCurrentTimeByDateType(dateType:DateType) : Date {
        val ymdFormatter = "yyyy/MM/dd"
        val hmsFormatter = "HH:mm:ss"
        val ymdFormat = SimpleDateFormat(ymdFormatter)
        //val hmsFormat = SimpleDateFormat(hmsFormatter)
        val dateFormat = SimpleDateFormat("$ymdFormatter $hmsFormatter")
//        val today = Calendar.getInstance().time
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]
        val mHour = c[Calendar.HOUR]
        val mMinute = c[Calendar.MINUTE]
        val mSecond = c[Calendar.SECOND]
        val recentMonth = 1
        val day = "$mYear/$mMonth/$mDay"
        val time = "$mHour:$mMinute:$mSecond"
        val toDate = "$mYear/${if(mMonth-recentMonth<=0) kotlin.math.abs(mMonth - recentMonth)+1 else {mMonth-recentMonth}}/$mDay"
        val fromDate =  "$mYear/$mMonth/$mDay"
        val now = "$day $time"
        return when (dateType){
            DateType.TO_DATE -> ymdFormat.parse(toDate)!!
            DateType.FROM_DATE -> ymdFormat.parse(fromDate)!!
            DateType.NOW -> ymdFormat.parse(now)!!
        }
    }
}