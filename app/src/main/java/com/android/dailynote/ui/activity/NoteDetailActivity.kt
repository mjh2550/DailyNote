package com.android.dailynote.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.databinding.ActivityNoteDetailBinding
import com.android.dailynote.ui.viewmodel.NoteViewViewModel

class NoteDetailActivity : BaseActivity<ActivityNoteDetailBinding,NoteViewViewModel>() {
    override fun getLayoutRes()= R.layout.activity_note_detail

    override val mViewModel: NoteViewViewModel  by viewModels()

    override fun subscribeUi() {
        val noteIntent = intent.extras?.get("NOTE_ID")

        with(mViewModel){

        }
        with(mDataBinding){

        }
    }
}