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

    suspend fun getNoteListByDayOfMonthFlow(pickToDate:Calendar, pickFromDate:Calendar)
            = withContext(CoroutineScope(Dispatchers.Default).coroutineContext) {
        db.noteDao().getNoteListByDayOfMonthFlow(pickToDate.timeInMillis, pickFromDate.timeInMillis)
    }
    suspend fun getNoteListByDayOfMonth(pickToDate:Calendar, pickFromDate:Calendar)
            = withContext(CoroutineScope(Dispatchers.Default).coroutineContext){
        db.noteDao().getNoteListByDayOfMonth(pickToDate.timeInMillis, pickFromDate.timeInMillis)
    }

    suspend fun insertData(noteVO: NoteVO)
        = withContext(CoroutineScope(Dispatchers.Default).coroutineContext){
        db.noteDao().insert(noteVO = noteVO)
    }

    suspend fun updateNoteContents(noteVO: NoteVO)
        = withContext(CoroutineScope(Dispatchers.Default).coroutineContext){
            db.noteDao().update(noteVO)
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
