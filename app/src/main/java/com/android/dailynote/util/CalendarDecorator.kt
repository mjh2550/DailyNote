package com.android.dailynote.util

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.drawable.Drawable
import com.android.dailynote.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.ArrayList

class CalendarDecorator(private val context: Activity?, private val dates: ArrayList<CalendarDay>) : DayViewDecorator {
    @SuppressLint("UseCompatLoadingForDrawables")
    private val drawable = context?.getDrawable(R.drawable.ic_baseline_circle_24)!! //이미지
    private val dateList = dates.toHashSet()

    override fun shouldDecorate(day: CalendarDay?) :Boolean {
        return dateList.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view?.setBackgroundDrawable(drawable)//이미지
    }
}