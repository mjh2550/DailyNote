package com.android.dailynote.ui.fragment

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.android.dailynote.base.BaseFragment
import java.util.*

class DatePickerFragment(val cal: Calendar = Calendar.getInstance(),
                         val listener : DatePickerDialog.OnDateSetListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        DatePickerDialog(requireActivity(),listener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH))
}