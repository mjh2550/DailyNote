package com.android.dailynote.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.CheckBox
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
import com.android.dailynote.adapters.EventType
import com.android.dailynote.adapters.NoteListAdapter
import com.android.dailynote.adapters.NoteListListener
import com.android.dailynote.base.BaseFragment
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.data.network.util.ErrorUtil
import com.android.dailynote.databinding.FragmentNoteListBinding
import com.android.dailynote.ui.activity.HomeActivity
import com.android.dailynote.ui.activity.NoteWriteActivity
import com.android.dailynote.ui.viewmodel.NoteListViewModel
import java.util.*
import kotlin.collections.HashSet

class NoteListFragment : BaseFragment<FragmentNoteListBinding,NoteListViewModel>() ,OnClickListener , CompoundButton.OnCheckedChangeListener{

    override val mViewModel: NoteListViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  NoteListViewModel(repository = NoteRepository(applicationContext = context!!) ) as T
            }
        })[NoteListViewModel::class.java]
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val title = it.data?.getStringExtra("title")
            val contents = it.data?.getStringExtra("contents")

            //val now = Date(System.currentTimeMillis()).toString()
            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]
            val mHour = c[Calendar.HOUR]
            val mMinute = c[Calendar.MINUTE]
            val mSecond = c[Calendar.SECOND]
            val day = "$mYear/$mMonth/$mDay"
            val time = "$mHour:$mMinute:$mSecond"
            val today = "$day $time"
            mViewModel.insertData(NoteVO(
                null,
                title!!,
                contents!!,
                "test Writer",
                "Y",
                null,
                null,
                today,
                "Y",
                null,
                null,
                false,
            ))

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
            NoteListListener { v , eventType ->
                when(eventType){
                    EventType.ON_BUTTON_CLICK -> {
                         Toast.makeText(requireContext(),"ON_BUTTON_CLICK",Toast.LENGTH_SHORT).show()
                    }
                    EventType.ON_CHECKBOX_CHANGED -> {
                        Toast.makeText(requireContext(),"ON_CHECKBOX_CHANGED",Toast.LENGTH_SHORT).show()
                    }
                    EventType.ON_RADIOBUTTON_CLICK -> {
                        Toast.makeText(requireContext(),"ON_RADIOBUTTON_CLICK",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        )

        with(mViewModel){
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
//            cbAllCheck.setOnCheckedChangeListener(this@NoteListFragment)

            val c = Calendar.getInstance()
            val mYear = c[Calendar.YEAR]
            val mMonth = c[Calendar.MONTH]
            val mDay = c[Calendar.DAY_OF_MONTH]
            val recentMonth = 1
            val toDate = "$mYear/${if(mMonth-recentMonth<=0) kotlin.math.abs(mMonth - recentMonth)+1 else {mMonth-recentMonth}}/$mDay"
            val fromDate = "$mYear/$mMonth/$mDay"
            btnToDate.text = toDate
            btnFromDate.text = fromDate
        }

    }
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_add -> onClickFloatingButton(R.id.btn_add)
            R.id.btn_del -> onClickFloatingButton(R.id.btn_del)
            R.id.btn_more -> onClickFloatingButton(R.id.btn_more)
        }
    }

    private fun onClickFloatingButton (btnId : Int){
        when (btnId){
            R.id.btn_add -> {
                val intent = Intent(requireActivity(),NoteWriteActivity::class.java)
        //        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //        startActivity(intent)
                startForResult.launch(intent)
            }
            R.id.btn_del-> {
                //TODO 삭제 버튼 클릭 시
                // 현재 체크되어있는 리스트의 id를 조사하여 리스트를 만들고, Delete에 리스트를 넘김
//                mDataBinding.vm.deleteList
                println("${mViewModel.dataList.value!!.size}")
                for (noteVo in mViewModel.dataList.value!!){
                    println("ischecked : ${noteVo.isChecked}")
                }
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
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCheckedChanged(cBtn: CompoundButton?, isChecked: Boolean) {
       when (cBtn?.id){
           //전체 체크 박스 클릭 시
           R.id.cb_all_check -> {
               mDataBinding.vm?.onCheckBoxChanged(isChecked)
               //UI Update
               mDataBinding.lvNoteItem.adapter?.notifyDataSetChanged()
           }
       }
    }


}