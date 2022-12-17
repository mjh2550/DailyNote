package com.android.dailynote.ui.fragment

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.android.dailynote.base.BaseFragment
import java.util.*

class DatePickerFragment(
    private val cal: Calendar = Calendar.getInstance()) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return DatePickerDialog(requireActivity(),
            this,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH))
    }

    //OK 선택 시
    //TODO 데이터 변화
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {

    }

}