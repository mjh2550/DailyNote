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

    var toDate = TimeClass().getCurrentTimeToDate(Calendar.getInstance(),DateType.TO_DATE)
    var fromDate = TimeClass().getCurrentTimeToDate(Calendar.getInstance(),DateType.FROM_DATE)
    var dataList = repository.getNoteListByDate(toDate,fromDate)
    var schToDate = ""
    var schFromDate = ""

    fun insertData(noteVO: NoteVO) = repository.insertData(noteVO)
    fun deleteAll() = repository.deleteAll()
    fun deleteById(noteId: Int) = repository.deleteById(noteId)

    fun click() {
        println("OK")
    }

    fun clickBtnDate(dateType: DateType){
        //TODO 날짜 세팅 버튼 클릭 시 이벤트
        // 달력 출력 후 클릭한 연월일을 YY/MM/DD 로 버튼 텍스트 출력
        when(dateType){
            DateType.TO_DATE -> {
                println("to_date")
//                schToDate = ""
            }
            DateType.FROM_DATE -> {
                println("from_date")
//                schFromDate = ""
            }
            else -> {

            }
        }
    }

    fun clickBtnSearch(){


        /*toDate = if(schToDate.isNotEmpty()) TimeClass().stringToDate(schToDate,"yyyy/mm/dd HH:mm:ss")
        else TimeClass().getCurrentTimeToDate(Calendar.getInstance(), DateType.TO_DATE)

        fromDate = if(schFromDate.isNotEmpty()) TimeClass().stringToDate(schFromDate,"yyyy/mm/dd HH:mm:ss")
        else TimeClass().getCurrentTimeToDate(Calendar.getInstance(), DateType.FROM_DATE)*/

        toDate = TimeClass().getCurrentTimeToDate(Calendar.getInstance(),DateType.TO_DATE)
        println("$toDate $fromDate")
        dataList = repository.getNoteListByDate(toDate,fromDate)
        println("${dataList.value?.size}")
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