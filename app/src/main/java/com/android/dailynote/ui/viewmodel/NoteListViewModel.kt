package com.android.dailynote.ui.viewmodel

import com.android.dailynote.BuildConfig
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.common.DateType
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*


class NoteListViewModel(private val repository: NoteRepository) : BaseViewModel() {

    private val FIREBASE_DB_URL = BuildConfig.FIREBASE_URL
    private val database = Firebase.database(FIREBASE_DB_URL)
    private val myRef = database.getReference("daily_note")

    // var dataList1 = MutableLiveData<NoteVO>()
    var deleteList = emptyList<NoteVO>()
//    val dataList = repository.getAllNoteList()
    //TODO 검색 시 안나옴
    val dataList = repository.getNoteListByDate(TimeClass().getCurrentTimeByDateType(DateType.TO_DATE)
                                            ,TimeClass().getCurrentTimeByDateType(DateType.FROM_DATE))

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