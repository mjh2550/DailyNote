package com.android.dailynote.data.model.roomdb

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.dailynote.data.model.entity.NoteVO

class NoteRepository(var application: Application,
                     var db : NoteRoomDatabase? = NoteRoomDatabase.getDatabase(application),
                     var noteDao: NoteDao? = db?.noteDao(),
                     var noteVO: NoteVO? = null,
                     var noteList : LiveData<List<NoteVO>> = MutableLiveData(emptyList())){

    fun getAllNoteList() : LiveData<List<NoteVO>>{
        return noteList
    }

    fun insert(noteVO: NoteVO?){
//        WordRoomDatabase.databaseWriteExecutor.execute { mWordDao.insert(word) }

    }
}