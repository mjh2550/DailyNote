package com.android.dailynote.ui.activity

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.common.DateType
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.ActivityNoteDetailBinding
import com.android.dailynote.ui.viewmodel.NoteDetailViewModel
import java.text.SimpleDateFormat
import java.util.*

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
        val noteId = intent.extras?.getLong("NOTE_ID")
        with(mViewModel){
            loadData(noteId!!)
        }
        with(mDataBinding){
            vm = mViewModel
            val backArrow =  ContextCompat.getDrawable(this@NoteDetailActivity, R.drawable.ic_baseline_arrow_back_24);
            setSupportActionBar(detailTitleBar.toolbar)
            supportActionBar?.setHomeAsUpIndicator(backArrow)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        mViewModel.noteContents.observe(this@NoteDetailActivity){
            val regTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(it.regTime) as String
            mDataBinding.detailTitleBar.titleText.text = it?.noteTitle
            mDataBinding.tvNoteTitle.text = it?.noteTitle
            mDataBinding.tvNoteContents.text = it?.noteContents
            mDataBinding.tvNoteWriter.text = it?.noteWriter
            mDataBinding.tvRegTime.text = regTime
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.titlebar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.toolbar_menu_edit -> {
                val intentWriteActivity = Intent(this@NoteDetailActivity, NoteWriteActivity::class.java)
                intentWriteActivity.putExtra("NOTE_ID", intent.extras?.getLong("NOTE_ID"))
                intentWriteActivity.putExtra("NOTE_TITLE", mDataBinding.tvNoteTitle.text)
                intentWriteActivity.putExtra("NOTE_CONTENTS", mDataBinding.tvNoteContents.text)
                callbackEditPage.launch(intentWriteActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val callbackEditPage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                val id = it.data?.getLongExtra("id",0)
                val title = it.data?.getStringExtra("title")
                val contents = it.data?.getStringExtra("contents")
                val now = TimeClass().getCurrentTimeToDate(Calendar.getInstance(), DateType.NOW).time
                if(id?.toInt() != 0) {
                    mViewModel.updateData(
                        NoteVO(
                            id, title!!, contents!!, "test Writer", "Y",
                            null, now, now, "Y", null,
                            now, false,
                        )
                    )
                    mViewModel.loadData(id!!)
                }
//                mDataBinding.lvNoteItem.adapter?.notifyDataSetChanged()
            }
        }
}