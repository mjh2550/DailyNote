package com.android.dailynote.ui.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.*
import com.android.dailynote.BuildConfig
import com.android.dailynote.R
import com.android.dailynote.base.BaseApplication
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.firebase.FireBaseDataSource
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.ui.activity.HomeActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class NoteListViewModel(private val repository: NoteRepository) : BaseViewModel() {

    private val FIREBASE_DB_URL = BuildConfig.FIREBASE_URL
    private val database = Firebase.database(FIREBASE_DB_URL)
    private val myRef = database.getReference("daily_note")
//    var dataList1 = MutableLiveData<NoteVO>()
    var deleteList = emptyList<NoteVO>()

    val dataList = repository.getAllNoteList()

    fun insertData(noteVO: NoteVO) = repository.insertData(noteVO)

    fun deleteAll() = repository.deleteAll()

    fun deleteById(noteId: Int) = repository.deleteById(noteId)

    fun click() {
        println("OK")
    }

    fun clickBtnDate(){
        //TODO 날짜 세팅 버튼 클릭 시 이벤트
        // 달력 출력 후 클릭한 연월일을 YY/MM/DD 로 버튼 텍스트 출력
    }

    fun clickBtnSearch(){
        //TODO
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