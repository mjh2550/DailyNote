package com.android.dailynote.data.model.roomdb

import android.content.Context
import androidx.room.Room
import com.android.dailynote.data.model.entity.NoteVO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NoteRepository(applicationContext : Context){

    private val db = Room.databaseBuilder(
        applicationContext,
        NoteRoomDatabase::class.java, "note_db"
    ).build()

    fun getAllNoteList() = db.noteDao().getAllNoteList()

    suspend fun getNoteListByDateList(toDate:Date, fromDate: Date)
    = withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
        db.noteDao().getNoteListByDateList(toDate, fromDate)
    }

    suspend fun getNoteContentsByNoteId(noteId:Long)
            = withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
        db.noteDao().getNoteContentsByNoteId(noteId)
    }

    fun getNoteListByDateFlow(toDate:Date, fromDate: Date) = db.noteDao().getNoteListByDateFlow(toDate, fromDate)

    suspend fun getNoteListByDayOfMonthFlow(dayOfMonth:Date)
            = withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
        db.noteDao().getNoteListByDayOfMonthFlow(dayOfMonth)
    }
    suspend fun getNoteListByDayOfMonth(dayOfMonth:Date)
            = withContext(CoroutineScope(Dispatchers.Default).coroutineContext){
        db.noteDao().getNoteListByDayOfMonth(dayOfMonth)
    }

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

    suspend fun deleteByList(deleteList : List<NoteVO>)
    = withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
        val targetId = mutableListOf<String>()
        for((idx, list) in deleteList.withIndex()){
            targetId.add(idx,list.noteId.toString())
        }
        db.noteDao().deleteByList(deleteList = targetId)
    }
}