package com.android.dailynote.data.model.roomdb

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.android.dailynote.data.model.entity.NoteVO
import retrofit2.http.DELETE

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY note_id ASC")
    fun getAllNoteList() : LiveData<List<NoteVO>>

    @Query("SELECT * FROM note_table " +
            "WHERE 1=1 "+
            "AND reg_time BETWEEN :toDate AND :fromDate "+
            "ORDER BY note_id ASC")
    fun getNoteListByDate(toDate : String , fromDate : String) : LiveData<List<NoteVO>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(noteVO: NoteVO)

    @Query("DELETE FROM note_table WHERE note_id = :noteId")
    fun deleteById(noteId : Int)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("DELETE FROM note_table WHERE note_id IN (:deleteList)")
    fun deleteByList(deleteList : List<Int> )

//    @Update
//    fun update(noteVO: NoteVO)

}