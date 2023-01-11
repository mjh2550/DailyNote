package com.android.dailynote.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.ActivityNoteDetailBinding
import com.android.dailynote.ui.viewmodel.NoteDetailViewModel
import com.android.dailynote.ui.viewmodel.NoteListViewModel
import com.android.dailynote.ui.viewmodel.NoteViewViewModel

class NoteDetailActivity : BaseActivity<ActivityNoteDetailBinding,NoteDetailViewModel>() {
    override fun getLayoutRes()= R.layout.activity_note_detail

    override val mViewModel: NoteDetailViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  NoteDetailViewModel(repository = NoteRepository(applicationContext = applicationContext) ) as T
            }
        })[NoteDetailViewModel::class.java]
    }

    override fun subscribeUi() {
        val noteIntent = intent.extras?.get("NOTE_ID").toString()
        val noteId = noteIntent.toLong()
        with(mViewModel){
            loadData(noteId)
        }
        with(mDataBinding){
            vm = mViewModel
        }
        mViewModel.noteContents.observe(this@NoteDetailActivity){
            mDataBinding.tvNoteTitle.text = it.noteTitle
            mDataBinding.tvNoteContents.text = it.noteContents
        }
    }
}