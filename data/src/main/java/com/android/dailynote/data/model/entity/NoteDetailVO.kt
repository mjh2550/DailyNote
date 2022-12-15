package com.android.dailynote.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*
import javax.annotation.Nonnull

data class NoteDetailVO(
    ///id
    @PrimaryKey(autoGenerate = true)
    @Nonnull
    @ColumnInfo(name = "note_id")
    val noteId : Long?,

    ///제목
    @ColumnInfo(name = "note_title")
    val noteTitle : String,

    ///내용
    @ColumnInfo(name = "note_contents")
    val noteContents : String,

    ///글쓴이
    @ColumnInfo(name = "note_writer")
    val noteWriter : String,

    ///첨부 여부
    @ColumnInfo(name = "attach_yn")
    val attachYN : String,

    ///첨부파일 경로
    @ColumnInfo(name = "attach_path")
    val attachPath : String?,

    ///수정일
    @ColumnInfo(name = "edit_time")
    val editTime : Date,

    ///생성일
    @ColumnInfo(name = "reg_time")
    val regTime : Date,

    ///코멘트 여부
    @ColumnInfo(name = "comment_yn")
    val commentYN : String,

    ///코멘트 내용
    @ColumnInfo(name = "comment_contents")
    val commentContents : String?,

    ///코멘트 시각
    @ColumnInfo(name = "comment_time")
    val commentTime : Date,

    ///상위 id
    @ColumnInfo(name = "parent_id")
    val parentId : String?,

)
