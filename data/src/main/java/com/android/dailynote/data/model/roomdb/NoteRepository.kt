package com.android.dailynote.data.model.roomdb

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.room.Room
import com.android.dailynote.data.model.entity.NoteVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteRepository(applicationContext : Context){

    private val db = Room.databaseBuilder(
        applicationContext,
        NoteRoomDatabase::class.java, "note_db"
    ).build()

    fun getAllNoteList() = db.noteDao().getAllNoteList()

    fun insertData(noteVO: NoteVO) {
        CoroutineScope(Dispatchers.IO).launch {
            db.noteDao().insert(noteVO = noteVO)
        }
    }

    fun deleteAll() = db.noteDao().deleteAll()

    fun deleteById(noteId:Int) = db.noteDao().deleteById(noteId = noteId)
}