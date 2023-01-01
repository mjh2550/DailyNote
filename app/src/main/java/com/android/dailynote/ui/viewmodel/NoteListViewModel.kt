package com.android.dailynote.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.util.*


class NoteListViewModel(private val repository: NoteRepository) : BaseViewModel() {

    companion object {
        private const val FIREBASE_DB_URL = BuildConfig.FIREBASE_URL
        private val database = Firebase.database(FIREBASE_DB_URL)
        private val myRef = database.getReference("daily_note")
    }

    //    val dataList = repository.getAllNoteList()
    var toDate = TimeClass().getCurrentTimeToDate(Calendar.getInstance(),DateType.TO_DATE,1)
    var fromDate = TimeClass().getCurrentTimeToDate(Calendar.getInstance(),DateType.FROM_DATE)
    private val _dataList :MutableLiveData<List<NoteVO>> = MutableLiveData(emptyList())
    val dataList : LiveData<List<NoteVO>> get() = _dataList
    var deleteList = listOf<NoteVO>()

    fun loadData() = viewModelScope.launch {
        val result = searchData()
        _dataList.value = result
//        _dataList.postValue(result)
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

    fun clickBtnSearch() = loadData()

    fun onCheckBoxAllChanged(isChecked : Boolean){
        if(dataList.value != null && dataList.value!!.isNotEmpty()) {
            for (listNoteVo in dataList.value!!) {
                println("${listNoteVo.noteId} ${listNoteVo.isChecked} -> $isChecked")
                listNoteVo.isChecked = isChecked
            }
        }
    }

}