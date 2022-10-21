package com.android.dailynote.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.ActivityNoteViewBinding
import com.android.dailynote.databinding.ActivityNoteWriteBinding
import com.android.dailynote.ui.fragment.NoteListFragment
import com.android.dailynote.ui.viewmodel.NoteListViewModel
import com.android.dailynote.ui.viewmodel.NoteWriteViewModel

class NoteWriteActivity : BaseActivity<ActivityNoteWriteBinding,NoteWriteViewModel>() ,OnClickListener {
    override val mViewModel: NoteWriteViewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  NoteWriteViewModel(repository = NoteRepository(applicationContext = applicationContext) ) as T
            }
        })[NoteWriteViewModel::class.java]
    }

    override fun getLayoutRes() = R.layout.activity_note_write

    override fun subscribeUi() {
        with(mViewModel){
        }
        with(mDataBinding){
            vm = mViewModel
            titleBar.toolbar.title = "일기장 작성"
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
        intent.putExtra("title",mViewModel.edtTitleText.value)
        intent.putExtra("contents",mViewModel.edtContentsText.value)
        setResult(RESULT_OK,intent)
        if(!isFinishing)finish()
    }

}