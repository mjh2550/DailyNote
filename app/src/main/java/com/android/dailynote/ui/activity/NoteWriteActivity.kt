package com.android.dailynote.ui.activity

import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.ActivityNoteWriteBinding
import com.android.dailynote.ui.viewmodel.NoteWriteViewModel

class NoteWriteActivity : BaseActivity<ActivityNoteWriteBinding,NoteWriteViewModel>() ,OnClickListener {
    override val mViewModel: NoteWriteViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  NoteWriteViewModel(repository = NoteRepository(applicationContext = applicationContext) ) as T
            }
        })[NoteWriteViewModel::class.java]
    }

    enum class NoteState {
        STATE_WRITE, STATE_EDIT
    }
    lateinit var noteState: NoteState
    private var noteId : Long = 0

    override fun getLayoutRes() = R.layout.activity_note_write

    override fun subscribeUi() {
        with(mViewModel){
        }
        with(mDataBinding){
            vm = mViewModel
            noteId = intent.extras?.getLong("NOTE_ID") ?: 0
            noteState = if(noteId.toInt() == 0) NoteState.STATE_WRITE
            else NoteState.STATE_EDIT

            val titleText = if(noteState == NoteState.STATE_WRITE)
                "일기장 작성" else "일기장 수정"

            if(noteState == NoteState.STATE_EDIT){
                val noteTitleText = intent.extras?.getString("NOTE_TITLE") ?: ""
                val noteContentsText = intent.extras?.getString("NOTE_CONTENTS") ?: ""
                mViewModel.edtTitleText.value = noteTitleText
                mViewModel.edtContentsText.value = noteContentsText
            }

            noteWriteTitleBar.titleText.text = titleText
            btnCancel.setOnClickListener(this@NoteWriteActivity)
            btnSave.setOnClickListener(this@NoteWriteActivity)
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_cancel -> onClickCancelButton()
            R.id.btn_save -> onClickSaveButton()
        }
    }

    private fun onClickCancelButton() {
        val intent = Intent()
        setResult(RESULT_CANCELED,intent)
        finish()
    }

    private fun onClickSaveButton(){
        val intent = Intent()
        intent.putExtra("id", noteId)
        intent.putExtra("title", mViewModel.edtTitleText.value)
        intent.putExtra("contents", mViewModel.edtContentsText.value)
        setResult(RESULT_OK,intent)
        if(!isFinishing)finish()
    }

}