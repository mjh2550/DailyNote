package com.android.dailynote.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class NoteListViewModel : BaseViewModel() {

//    companion object{
        private val FIREBASE_DB_URL = BuildConfig.FIREBASE_URL
        private val database = Firebase.database(FIREBASE_DB_URL)
        private val myRef = database.getReference("daily_note")
//        val repository = NoteRepository(activity)
//    }

    //TODO TEST LIST
    val today = Date(System.currentTimeMillis()).toString()
    //    val testVO = NoteVO(
//        1,
//        "testTitle",
//        "empty contents",
//        "test Writer",
//        "Y",
//        null,
//        null,
//        today,
//        "Y",
//        null,
//        null
//    )
    val testVO2 = NoteVO(
        2,
        "testTitle2",
        "empty contents",
        "test Writer",
        "Y",
        null,
        null,
        today,
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
        today,
        "Y",
        null,
        null
    )
    val list = arrayListOf(testVO2,testVO3)
    val dataList = MutableLiveData(list)
//        repository.getAllNoteList()

//    fun insert(noteVO: NoteVO) = repository.insert(noteVO)


    fun click() {
        println("OK")
    }

}