package com.android.dailynote.common

import java.util.*

class TimeClass {

    fun getCurrentTimeByDateType(dateType:DateType) : String {
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
        return when (dateType){
            DateType.TO_DATE -> {
                "$mYear/${if(mMonth-recentMonth<=0) kotlin.math.abs(mMonth - recentMonth)+1 else {mMonth-recentMonth}}/$mDay"
            }
            DateType.FROM_DATE -> {
                "$mYear/$mMonth/$mDay"
            }
            DateType.NOW -> {
                "$day $time"
            }
        }
    }
}