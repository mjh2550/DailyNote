package com.android.dailynote.data.model.roomdb

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.android.dailynote.data.model.entity.NoteVO

@Database(
    entities = [
    NoteVO::class
               ],
    version = 1)
abstract class NoteRoomDatabase :RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object{
        private var INSTANCE : NoteRoomDatabase? = null
        fun getDatabase(context: Context) : NoteRoomDatabase? {
            if(INSTANCE == null){
                synchronized(NoteRoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    NoteRoomDatabase::class.java, "note.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}