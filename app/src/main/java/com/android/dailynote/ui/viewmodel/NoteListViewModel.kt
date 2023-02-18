package com.android.dailynote.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.dailynote.BuildConfig
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.common.DateType
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import java.util.*


class NoteListViewModel(private val repository: NoteRepository) : BaseViewModel() {

    companion object {
        private const val FIREBASE_DB_URL = BuildConfig.FIREBASE_URL
        private val database = Firebase.database(FIREBASE_DB_URL)
        private val myRef = database.getReference("daily_note")
    }

    //    val dataList = repository.getAllNoteList()
    var toDate: Date = TimeClass().getCurrentTimeToDate(Calendar.getInstance(),DateType.TO_DATE,1).time
    var fromDate: Date = TimeClass().getCurrentTimeToDate(Calendar.getInstance(),DateType.FROM_DATE).time
    private val _dataList :MutableLiveData<List<NoteVO>> = MutableLiveData(emptyList())
    val dataList : LiveData<List<NoteVO>> get() = _dataList
    var deleteList = listOf<NoteVO>()

    fun loadData() = viewModelScope.launch {
        val searchResult = searchData()
        _dataList.value = searchResult
//        _dataList.postValue(result)
        isLoading.postValue(false)
    }

    fun deleteList() = viewModelScope.launch {
        deleteData()
        loadData()
    }
    private suspend fun searchData() = repository.getNoteListByDateList(toDate, fromDate)
    private suspend fun deleteData() = repository.deleteByList(deleteList = deleteList)

    fun insertData(noteVO: NoteVO) = repository.insertData(noteVO)
    fun deleteAll() = repository.deleteAll()
    fun deleteById(noteId: Int) = repository.deleteById(noteId)

    //TODO DELETE
    fun click() {
        println("OK")
    }

    fun onClickBtnSearch() {
        isLoading.postValue(true)
        loadData()
    }

    fun onCheckBoxAllChanged(isChecked : Boolean){
        if(dataList.value != null && dataList.value!!.isNotEmpty()) {
            for (listNoteVo in dataList.value!!) {
                println("${listNoteVo.noteId} ${listNoteVo.isChecked} -> $isChecked")
                listNoteVo.isChecked = isChecked
            }
        }
    }

}