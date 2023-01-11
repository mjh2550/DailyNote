package com.android.dailynote.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import kotlinx.coroutines.launch

class NoteDetailViewModel(private val repository: NoteRepository) : BaseViewModel() {
    val noteContents : LiveData<NoteVO> get() = _noteContents
    var _noteContents = MutableLiveData<NoteVO>()

    private suspend fun loadContents(noteId: Long)
            = repository.getNoteContentsByNoteId(noteId = noteId)

    fun loadData(noteId: Long){
        viewModelScope.launch {
            _noteContents.value = loadContents(noteId)
        }
    }
}