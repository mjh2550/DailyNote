package com.android.dailynote.ui.activity

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.ActivityNoteDetailBinding
import com.android.dailynote.ui.viewmodel.NoteDetailViewModel

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
            setSupportActionBar(titleBar.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mViewModel.noteContents.observe(this@NoteDetailActivity){
            mDataBinding.titleBar.toolbar.title = it.noteTitle
            mDataBinding.tvNoteTitle.text = it.noteTitle
            mDataBinding.tvNoteContents.text = it.noteContents
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //toolbar의 back키 눌렀을 때 동작
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}