package com.android.dailynote.ui.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.view.View.OnClickListener
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.ActivityNoteWriteBinding
import com.android.dailynote.ui.viewmodel.NoteWriteViewModel
import java.util.*

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
        if(isValidateData()){
            when(p0?.id){
                R.id.btn_cancel -> showAlertDialog(false)
                R.id.btn_save -> showAlertDialog(true)
            }
        }
    }

    private fun onClickCancel() {
        val intent = Intent()
        setResult(RESULT_CANCELED,intent)
        finish()
    }

    private fun onClickSave(){
        val intent = Intent()
        intent.putExtra("id", noteId)
        intent.putExtra("title", mViewModel.edtTitleText.value)
        intent.putExtra("contents", mViewModel.edtContentsText.value)
        setResult(RESULT_OK,intent)
        if(!isFinishing)finish()
    }

    private fun isValidateData() : Boolean{
        if(mDataBinding.edtNoteTitle.text.isEmpty()){
            val now = TimeClass().dateToString(Date(),"YYYY-MM-dd")
            val arr = now.split("-")
            mDataBinding.edtNoteTitle.setText("${arr[0]}년 ${arr[1]}월 ${arr[2]}일의 일기")
        }else if(mDataBinding.edtNoteContents.text.isEmpty()){
            //Error alert
            Toast.makeText(this@NoteWriteActivity,"내용이 비어있습니다.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun showAlertDialog(isSave : Boolean) {
        //TODO 디자인 커스텀 필요
        if(isSave) {
            val builder = AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("입력한 데이터를 저장하시겠습니까?")
                .setPositiveButton("확인") { dialog, which -> onClickSave() }
                .setNegativeButton("취소") { dialog, which -> dialog.dismiss() }
                .show()
        } else {
            val builder = AlertDialog.Builder(this)
                .setTitle("알림")
                .setMessage("입력하던 데이터를 초기화하고 메인으로 이동하시겠습니까?\n초기화한 데이터는 저장되지 않습니다.")
                .setPositiveButton("확인") { dialog, which -> onClickCancel() }
                .setNegativeButton("취소") { dialog, which -> dialog.dismiss() }
                .show()
        }
    }

}