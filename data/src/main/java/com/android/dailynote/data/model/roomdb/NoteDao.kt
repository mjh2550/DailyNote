package com.android.dailynote.data.model.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.dailynote.data.model.entity.NoteVO
import retrofit2.http.DELETE

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_table ORDER BY reg_time DESC")
    fun getAllNoteList() : LiveData<List<NoteVO>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(noteVO: NoteVO)

    @Query("DELETE FROM note_table WHERE note_id = :noteId")
    fun deleteById(noteId : Int)

    @Query("DELETE FROM note_table")
    fun deleteAll()

//    @Update
//    fun update(noteVO: NoteVO)

}