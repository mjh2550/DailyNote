package com.android.dailynote.ui.viewmodel

import android.app.Activity
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.MutableLiveData
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.ui.activity.HomeActivity
import java.util.*

class NoteWriteViewModel(private val repository: NoteRepository) : BaseViewModel() {

//    val isDataOnLoad = MutableLiveData(false)
    val edtContentsText = MutableLiveData("")
    val edtTitleText = MutableLiveData("")
    private val now = TimeClass().dateToString(Date(), "YYYY-MM-dd").split("-")
    val dateStr = "${now[0]}년 ${now[1]}월 ${now[2]}일의 일기"
}