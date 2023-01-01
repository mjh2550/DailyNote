package com.android.dailynote.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.databinding.ActivityNoteDetailBinding
import com.android.dailynote.ui.viewmodel.NoteViewViewModel

class NoteDetailActivity(override val mViewModel: NoteViewViewModel) : BaseActivity<ActivityNoteDetailBinding,NoteViewViewModel>() {
    override fun getLayoutRes()= R.layout.activity_note_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
    }

    override fun subscribeUi() {
        with(mViewModel){

        }
        with(mDataBinding){

        }
    }
}