package com.android.dailynote.data.model.roomdb

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.dailynote.data.model.entity.NoteVO

class NoteRepository(var context: Context,
                     var noteList : LiveData<List<NoteVO>> = MutableLiveData(emptyList())){

    var db =  NoteRoomDatabase.getDatabase(context)
    var noteDao = db!!.noteDao()

    fun getAllNoteList() = noteList

    fun insert(noteVO: NoteVO){
          NoteRoomDatabase.databaseWriteExecutor.execute{
              noteDao!!.insert(noteVO)
          }
    }
}