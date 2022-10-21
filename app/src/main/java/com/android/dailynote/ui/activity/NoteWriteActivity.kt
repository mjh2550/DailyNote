package com.android.dailynote.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.databinding.ActivityNoteViewBinding
import com.android.dailynote.databinding.ActivityNoteWriteBinding
import com.android.dailynote.ui.viewmodel.NoteWriteViewModel

class NoteWriteActivity : BaseActivity<ActivityNoteWriteBinding,NoteWriteViewModel>() ,OnClickListener {
    override val mViewModel: NoteWriteViewModel by viewModels()

    override fun getLayoutRes() = R.layout.activity_note_write

    override fun subscribeUi() {
        with(mViewModel){

        }
        with(mDataBinding){
            vm = mViewModel
            titleBar.toolbar.title = "일기장 작성"
            btnCancel.setOnClickListener(this@NoteWriteActivity)
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_cancel -> onClickCancelButton()
        }
    }
    private fun onClickCancelButton() {
        finish()
    }

}