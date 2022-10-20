package com.android.dailynote.data.model.roomdb

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.dailynote.data.model.entity.NoteVO
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.Executors


@Database(
    entities = [
    NoteVO::class
               ],
    version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

   /* companion object{
        const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)
        private var INSTANCE : NoteRoomDatabase? = null
        fun getDatabase(context: Context) : NoteRoomDatabase? {
            if(INSTANCE == null){
                synchronized(NoteRoomDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    NoteRoomDatabase::class.java, "note.db")
                        .fallbackToDestructiveMigration()
//                        .addCallback(sRoomDatabaseCallback)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
        *//**
         * Override the onCreate method to populate the database.
         * For this sample, we clear the database every time it is created.
         *//*
        private val sRoomDatabaseCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                databaseWriteExecutor.execute {

                    // Populate the database in the background.
                    // If you want to start with more words, just add them.

                   *//* val dao = INSTANCE?.noteDao()
                    dao?.deleteAll()
                    val today = Date(System.currentTimeMillis()).toString()
                    val testVO = NoteVO(
                        1,
                        "testTitle",
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
                    dao?.insert(testVO)*//*
                }
            }
        }

    }*/


}