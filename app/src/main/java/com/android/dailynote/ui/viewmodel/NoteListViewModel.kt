package com.android.dailynote.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.dailynote.BuildConfig
import com.android.dailynote.R
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.firebase.FireBaseDataSource
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime

class NoteListViewModel : BaseViewModel(){

    companion object{
        private const val FIREBASE_DB_URL = BuildConfig.FIREBASE_URL
        private val database = Firebase.database(FIREBASE_DB_URL)
        private val myRef = database.getReference("daily_note")
    }

    //TODO TEST LIST
    val dateAndtime: LocalDateTime = LocalDateTime.now()
    val testVO = NoteVO(
        1,
        "testTitle",
        "empty contents",
        "test Writer",
        "Y",
        null,
        null,
        dateAndtime,
        "Y",
        null,
        null
    )
    val testVO2 = NoteVO(
        2,
        "testTitle2",
        "empty contents",
        "test Writer",
        "Y",
        null,
        null,
        dateAndtime,
        "Y",
        null,
        null
    )
    val testVO3 = NoteVO(
        3,
        "testTitle3",
        "empty contents",
        "test Writer",
        "Y",
        null,
        null,
        dateAndtime,
        "Y",
        null,
        null
    )
    val list = arrayListOf(testVO,testVO2,testVO3)
    val dataList = MutableLiveData<ArrayList<NoteVO>>(list)
    var cnt = 1
    fun click() {
        myRef.setValue("hello World ${cnt++}")
        println("OK")
    }

}