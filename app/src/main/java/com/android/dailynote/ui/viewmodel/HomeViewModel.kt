package com.android.dailynote.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.common.DateType
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel(private val repository: NoteRepository) : BaseViewModel() {

//    val noteList : LiveData<NoteVO> get() = _noteList
//    private val _noteList = MutableLiveData<NoteVO>()
    val titleName = "캘린더"

    var pickToDate = TimeClass().getCurrentTimeToDate(Calendar.getInstance(), DateType.TO_DATE)
    var pickFromDate = TimeClass().getCurrentTimeToDate(pickToDate, DateType.FROM_DATE)

    private val _pickDataList :MutableLiveData<List<NoteVO>> = MutableLiveData(emptyList())
    val pickDataList : LiveData<List<NoteVO>> get() = _pickDataList

    private fun loadValue() = viewModelScope.launch {
        val getPickResult = searchPickData()
        _pickDataList.value = getPickResult
//        println(getPickResult.size)
        isLoading.postValue(false)
        }

    private suspend fun searchPickData() = repository.getNoteListByDayOfMonth(pickToDate,pickFromDate)
//    private suspend fun searchPickFlowData() = repository.getNoteListByDayOfMonthFlow(pickToDate,pickFromDate)

    fun dateClick(year: Int, month: Int, dayOfMonth: Int){
        isLoading.postValue(true)
        //pick 한 날짜
        pickToDate = Calendar.getInstance()
        pickToDate.set(Calendar.YEAR, year)
        pickToDate.set(Calendar.MONTH, month)
        pickToDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        pickToDate.set(Calendar.HOUR_OF_DAY,0)
        pickToDate.set(Calendar.MINUTE,0)
        pickToDate.set(Calendar.SECOND,0)

        pickFromDate = Calendar.getInstance()
        pickFromDate.set(Calendar.YEAR, year)
        pickFromDate.set(Calendar.MONTH, month)
        pickFromDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        pickFromDate.set(Calendar.HOUR_OF_DAY,0)
        pickFromDate.set(Calendar.MINUTE,0)
        pickFromDate.set(Calendar.SECOND,0)
        pickFromDate.add(Calendar.DAY_OF_MONTH , 1)
        pickFromDate.add(Calendar.SECOND , -1)

//        pickToDate = TimeClass().getCurrentTimeToDate(pickCal,DateType.TO_DATE)
//        pickFromDate = TimeClass().getCurrentTimeToDate(pickToDate,DateType.FROM_DATE)

        loadValue()
    }
}