package com.android.dailynote.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.common.DateType
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val repository: NoteRepository) : BaseViewModel() {

//    val noteList : LiveData<NoteVO> get() = _noteList
//    private val _noteList = MutableLiveData<NoteVO>()

    var pickDate = TimeClass().getCurrentTimeToDate(Calendar.getInstance(), DateType.NOW)
    private val _pickDataList :MutableLiveData<List<NoteVO>> = MutableLiveData(emptyList())
    val pickDataList : LiveData<List<NoteVO>> get() = _pickDataList

    fun loadValue() = viewModelScope.launch {
        val getPickResult = searchPickData()
        _pickDataList.value = getPickResult
        }

    private suspend fun searchPickData() = repository.getNoteListByDayOfMonth(pickDate)
//    private suspend fun searchPickFlowData() = repository.getNoteListByDayOfMonthFlow(pickDate)

    fun dateClick(year: Int, month: Int, dayOfMonth: Int){
        // display the selected date in a Toast message
        val msg = "$dayOfMonth/${month + 1}/$year"
        println(msg)
        // add any additional logic to handle date clicks here
    }
}