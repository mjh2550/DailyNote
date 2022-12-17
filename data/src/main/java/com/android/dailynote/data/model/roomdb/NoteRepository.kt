package com.android.dailynote.data.model.roomdb

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Room
import com.android.dailynote.data.model.entity.NoteVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NoteRepository(applicationContext : Context){

    private val db = Room.databaseBuilder(
        applicationContext,
        NoteRoomDatabase::class.java, "note_db"
    )
        .build()

    fun getAllNoteList() = db.noteDao().getAllNoteList()
    fun getNoteListByDate(toDate:Date, fromDate: Date) = db.noteDao().getNoteListByDate(toDate, fromDate)

    fun insertData(noteVO: NoteVO) {
        CoroutineScope(Dispatchers.IO).launch {
            db.noteDao().insert(noteVO = noteVO)
        }
    }

    fun deleteAll()  {
        CoroutineScope(Dispatchers.IO).launch {
            db.noteDao().deleteAll()
        }
    }
    fun deleteById(noteId:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            db.noteDao().deleteById(noteId = noteId)
        }
    }
    fun deleteByList(deleteList : List<Int> ) {
        CoroutineScope(Dispatchers.IO).launch {
            db.noteDao().deleteByList(deleteList = deleteList)
        }
    }
}