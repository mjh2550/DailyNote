package com.android.dailynote.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.CalendarView
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isGone
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.dailynote.R
import com.android.dailynote.common.EventType
import com.android.dailynote.adapters.NoteListAdapter
import com.android.dailynote.adapters.NoteListListener
import com.android.dailynote.base.BaseFragment
import com.android.dailynote.common.DateType
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.data.network.util.ErrorUtil
import com.android.dailynote.databinding.FragmentNoteListBinding
import com.android.dailynote.ui.activity.NoteDetailActivity
import com.android.dailynote.ui.activity.NoteWriteActivity
import com.android.dailynote.ui.viewmodel.NoteListViewModel
import java.util.*
import kotlin.collections.ArrayList

class NoteListFragment : BaseFragment<FragmentNoteListBinding,NoteListViewModel>() ,OnClickListener , CompoundButton.OnCheckedChangeListener{

    override val mViewModel: NoteListViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  NoteListViewModel(repository = NoteRepository(applicationContext = context!!) ) as T
            }
        })[NoteListViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val title = it.data?.getStringExtra("title")
            val contents = it.data?.getStringExtra("contents")
            val now = TimeClass().getCurrentTimeToDate(Calendar.getInstance(),DateType.NOW).time
            mDataBinding.vm?.insertData(NoteVO(
                null,
                title!!,
                contents!!,
                "test Writer",
                "Y",
                null,
                now,
                now,
                "Y",
                null,
                now,
                false,
            ))
            mDataBinding.vm?.loadData()
            mDataBinding.lvNoteItem.adapter?.notifyDataSetChanged()

        } else {
            ErrorUtil.showErrorMessage(requireActivity(),getString(R.string.msg_save_fail)) {
                //callback code
            }
        }
    }

    /**
     * //TODO
     * 0.일기장 목록 CRUD 기능
        일기장 상단 필터링,검색 기능

        1.로그인 기능
        최초로그인 시 -> 핸드폰 인증 -> 인증성공시 휴대폰 번호가 ID + 간단한 정보 받고 로그인
        로그인 시 -> 휴대폰 번호 + 비밀번호 치고 로그인
        비회원 로그인 시 -> 본인 일기장 만 사용가능, 기기 맥주소 등록

        2.일기장 홈
        캘린더 , 커플

        내 정보 , 커플 설정

     */

    override fun getLayoutRes() = R.layout.fragment_note_list

    override fun subscribeUi() {

        val adapter = NoteListAdapter(
            NoteListListener { _, isChecked, noteVO, eventType ->
                when(eventType){
                    EventType.ON_BUTTON_CLICK -> {
//                        Toast.makeText(requireContext(),"ON_BUTTON_CLICK ${noteVO?.noteId}",Toast.LENGTH_SHORT).show()
                        val intent = Intent(requireActivity(),NoteDetailActivity::class.java)
                        intent.putExtra("NOTE_ID",noteVO?.noteId)
//                        startForResult.launch(intent)
                        startActivity(intent)
                    }
                    EventType.ON_CHECKBOX_CHANGED -> {
//                        Toast.makeText(requireContext(),"ON_CHECKBOX_CHANGED",Toast.LENGTH_SHORT).show()
                        for(listVo in mViewModel.dataList.value!!){
                            if(noteVO?.noteId == listVo.noteId){
                                listVo.isChecked = isChecked!!
                            }
                        }
                    }
                    EventType.ON_RADIOBUTTON_CLICK -> {
                        Toast.makeText(requireContext(),"ON_RADIOBUTTON_CLICK",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )

        with(mViewModel){
            loadData()
            dataList.observe(viewLifecycleOwner){
                it?.let{
                  adapter.submitList(it)
                }
            }
        }
        with(mDataBinding){
            vm = mViewModel
            lvNoteItem.adapter = adapter
            lvNoteItem.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            btnAdd.setOnClickListener(this@NoteListFragment)
            btnMore.setOnClickListener(this@NoteListFragment)
            btnDel.setOnClickListener(this@NoteListFragment)
            btnToDate.setOnClickListener(this@NoteListFragment)
            btnFromDate.setOnClickListener(this@NoteListFragment)
            btnSearch.setOnClickListener(this@NoteListFragment)
            cbAllCheck.setOnCheckedChangeListener(this@NoteListFragment)
            btnToDate.setOnClickListener(this@NoteListFragment)
            btnFromDate.setOnClickListener(this@NoteListFragment)

            btnToDate.text = TimeClass().getCurrentTimeToString(Calendar.getInstance(),DateType.TO_DATE,1)
            btnFromDate.text = TimeClass().getCurrentTimeToString(Calendar.getInstance(),DateType.FROM_DATE)
        }

    }
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(btnId: View?) {
        when (btnId?.id){
            R.id.btn_add -> {
                val intent = Intent(requireActivity(),NoteWriteActivity::class.java)
                //        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                //        startActivity(intent)
                startForResult.launch(intent)
            }
            R.id.btn_del-> {
                mViewModel.isLoading.value = true
                val list = mutableListOf<NoteVO>()
                var cnt = 0;
                for (noteVo in mDataBinding.vm?.dataList?.value!!){
                    if(noteVo.isChecked) {
                        list.add(cnt++,noteVo)
                    }
                }
                mDataBinding.vm?.deleteList = list
                mDataBinding.vm?.deleteList()
                mDataBinding.lvNoteItem.adapter?.notifyDataSetChanged()
            }
            R.id.btn_more -> {
                val isBtnGone = mDataBinding.btnAdd.isGone
                if(isBtnGone){
                    mDataBinding.btnAdd.visibility = View.VISIBLE
                    mDataBinding.btnAdd.isEnabled = true
                    mDataBinding.btnDel.visibility = View.VISIBLE
                    mDataBinding.btnDel.isEnabled = true
                }else{
                    mDataBinding.btnAdd.visibility = View.GONE
                    mDataBinding.btnAdd.isEnabled = false
                    mDataBinding.btnDel.visibility = View.GONE
                    mDataBinding.btnDel.isEnabled = false
                }
            }
            R.id.btn_to_date -> {
                loadCalendar(DateType.TO_DATE)
            }
            R.id.btn_from_date ->{
                loadCalendar(DateType.FROM_DATE)
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCheckedChanged(cBtn: CompoundButton?, isChecked: Boolean) {
       when (cBtn?.id){
           //전체 체크 박스 클릭 시
           R.id.cb_all_check -> {
               //Data Update
               mDataBinding.vm?.onCheckBoxAllChanged(isChecked)
               //UI Update
               mDataBinding.lvNoteItem.adapter?.notifyDataSetChanged()
           }
       }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadCalendar(dateType: DateType, cal : Calendar = Calendar.getInstance()) {

        if(dateType == DateType.TO_DATE) cal.time = mDataBinding.vm?.toDate
        else cal.time = mDataBinding.vm?.fromDate

//        val cal = Calendar.getInstance()
        val dialog = DatePickerFragment(cal = cal, listener = DatePickerDialog.OnDateSetListener {
                _, y, m, d ->
            cal.set(y,m,d)
            val currentYMD = "${y}/${m+1}/${d}"
            if(dateType == DateType.TO_DATE) {
                mDataBinding.btnToDate.text = currentYMD
                mDataBinding.vm?.toDate = cal.time
            }else if (dateType == DateType.FROM_DATE){
                mDataBinding.btnFromDate.text = currentYMD
                mDataBinding.vm?.fromDate = cal.time
            }
            mDataBinding.lvNoteItem.adapter?.notifyDataSetChanged()
        })
        dialog.show(childFragmentManager,"onCalLoad")

    }
}