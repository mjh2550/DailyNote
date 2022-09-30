package com.android.dailynote.ui.viewmodel

import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.data.model.entity.NoteVO
import java.time.LocalDateTime

class NoteListViewModel : BaseViewModel(){
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
}