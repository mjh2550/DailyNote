package com.android.dailynote.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*
import javax.annotation.Nonnull

@Entity(tableName = "note_table")
data class NoteVO(
    ///id
    @PrimaryKey
    @Nonnull
    @ColumnInfo(name = "note_id")
    val noteId : Long,

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
    val editTime : String?,

    ///생성일
    @ColumnInfo(name = "reg_time")
    val regTime : String,

    ///코멘트 여부
    @ColumnInfo(name = "comment_yn")
    val commentYN : String,

    ///코멘트 내용
    @ColumnInfo(name = "comment_contents")
    val commentContents : String?,

    ///코멘트 시각
    @ColumnInfo(name = "comment_time")
    val commentTime : String?,
)
