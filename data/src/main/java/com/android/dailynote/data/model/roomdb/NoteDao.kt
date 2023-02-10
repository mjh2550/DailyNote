package com.android.dailynote.data.model.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.dailynote.data.model.entity.NoteVO
import kotlinx.coroutines.flow.Flow
import java.util.*


@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY note_id DESC")
    fun getAllNoteList() : LiveData<List<NoteVO>>

    @Query("SELECT * FROM note_table " +
            "WHERE 1=1 "+
            "AND reg_time BETWEEN :toDate AND :fromDate "+
            "ORDER BY note_id ASC")
    fun getNoteListByDateFlow(toDate : Date, fromDate : Date) : Flow<List<NoteVO>>

    @Query("SELECT * FROM note_table " +
            "WHERE 1=1 "+
            "AND reg_time BETWEEN :pickToTime AND :pickFromTime "+
            "ORDER BY note_id ASC")
    fun getNoteListByDayOfMonthFlow(pickToTime : Long, pickFromTime: Long) : Flow<List<NoteVO>>

    @Query("SELECT * FROM note_table " +
            "WHERE 1=1 "+
            "AND reg_time BETWEEN :pickToTime AND :pickFromTime "+
            "ORDER BY note_id ASC")
    fun getNoteListByDayOfMonth(pickToTime : Long, pickFromTime: Long) : List<NoteVO>

    @Query("SELECT * FROM note_table " +
            "WHERE 1=1 "+
            "AND reg_time BETWEEN :toDate AND :fromDate "+
            "ORDER BY note_id DESC")
    fun getNoteListByDateList(toDate : Date, fromDate : Date) : List<NoteVO>

    @Query("SELECT * FROM note_table " +
           "WHERE note_id = :noteId")
    fun getNoteContentsByNoteId(noteId : Long) : NoteVO

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(noteVO: NoteVO)

    @Query("DELETE FROM note_table WHERE note_id = :noteId")
    fun deleteById(noteId : Int)

    @Query("DELETE FROM note_table")
    fun deleteAll() : Int

    @Query("DELETE FROM note_table WHERE note_id IN (:deleteList)")
    fun deleteByList( deleteList : MutableList<String> )

//    @Update
//    fun update(noteVO: NoteVO)

}