package com.android.dailynote.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.data.model.entity.NoteVO

class NoteViewViewModel : BaseViewModel() {
    val noteId : LiveData<NoteVO> get() = _noteId
    val _noteId = MutableLiveData<NoteVO>()

    fun loadData(){

    }
}