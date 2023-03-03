package com.android.dailynote.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.dailynote.R
import com.android.dailynote.adapters.HomeCalendarListAdapter
import com.android.dailynote.adapters.HomeCalendarListListener
import com.android.dailynote.base.BaseFragment
import com.android.dailynote.common.EventType
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.FragmentHomeBinding
import com.android.dailynote.ui.activity.NoteDetailActivity
import com.android.dailynote.ui.viewmodel.HomeViewModel
import com.android.dailynote.util.CalendarDecorator
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override val mViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  HomeViewModel(repository = NoteRepository(applicationContext = context!!) ) as T
            }
        })[HomeViewModel::class.java]
    }

    override fun getLayoutRes() = R.layout.fragment_home

    @SuppressLint("NotifyDataSetChanged", "SimpleDateFormat")
    override fun subscribeUi() {
        //로드 시 데이터 처리
        val titleText = activity?.findViewById(R.id.title_text) as TextView
        titleText.text = mViewModel.titleName

        val now = Calendar.getInstance()
        mViewModel.dateClick(now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH))


        val adapter = HomeCalendarListAdapter(
            HomeCalendarListListener { v,vo,type ->
                when(type){
                    EventType.ON_BUTTON_CLICK -> {
                        val intent = Intent(requireActivity(), NoteDetailActivity::class.java)
                        intent.putExtra("NOTE_ID", vo?.noteId)
                        startActivity(intent)
                    }
                    else -> {

                    }
                }
            }
        )

//        val adapter2 = HomeCalendarListAdapter(
//            HomeCalendarListListener { v,vo,type ->
//               println("${vo?.noteId} , $type")
//            }
//        )

        with(mViewModel){
            pickDayDataList.observe(viewLifecycleOwner){
                it?.let {
                    adapter.submitList(it)
                }

                if(it.isEmpty()){
                    mDataBinding.viewNoSearchData.root.visibility = View.VISIBLE
                } else {
                    mDataBinding.viewNoSearchData.root.visibility = View.GONE
                }
            }

            pickMonthDataList.observe(viewLifecycleOwner){
//                it?.let {
//                    adapter2.submitList(it)
//                }

                //캘린더 데이터 표시 처리
                if(it.isNotEmpty()){
                    val calList = ArrayList<CalendarDay>()
                    for(noteVO in it){
                        val editTime = noteVO.editTime
                        val sdfYear = SimpleDateFormat("yyyy").format(editTime)
                        val sdfMonth = SimpleDateFormat("MM").format(editTime)
                        val sdfDay = SimpleDateFormat("dd").format(editTime)
                        calList.add(CalendarDay.from(sdfYear.toInt(), sdfMonth.toInt() - 1, sdfDay.toInt()))
                    }
                    mDataBinding.calendarView.addDecorator(CalendarDecorator(requireActivity(),calList))
                }
            }
        }
        with(mDataBinding){
            vm = mViewModel
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            calendarView.setOnDateChangedListener { _, date, _ ->
                mViewModel.dateClick(date.year, date.month, date.day)
            }
            calendarView.setOnMonthChangedListener { _, date ->
                mViewModel.monthClick(date.year, date.month)
            }

        }
    }

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}