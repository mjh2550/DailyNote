package com.android.dailynote.data.model.entity

import java.time.LocalDateTime
import java.util.*

data class NoteVO(
    ///id
    val noteId : Long,
    ///제목
    val noteTitle : String,
    ///내용
    val noteContents : String,
    ///글쓴이
    val noteWriter : String,
    ///첨부 여부
    val attachYN : String,
    ///첨부파일 경로
    val attachPath : String?,
    ///수정일
    val editTime : LocalDateTime?,
    ///생성일
    val regTime : LocalDateTime,

    ///코멘트 여부
    val commentYN : String,
    ///코멘트 내용
    val commentContents : String?,
    ///코멘트 시각
    val commentTime : LocalDateTime?,
) {
    fun createTestVO(testNoteId : Long, testNoteTitle : String) = NoteVO(
        testNoteId,
        testNoteTitle,
        "empty contents",
        "test Writer",
        "Y",
        null,
        null,
        LocalDateTime.now(),
        "Y",
        null,
        null
    )
}
