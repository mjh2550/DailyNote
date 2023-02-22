package com.android.dailynote.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
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
import com.android.dailynote.common.TimeClass
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.FragmentHomeBinding
import com.android.dailynote.ui.viewmodel.HomeViewModel
import com.android.dailynote.ui.viewmodel.NoteListViewModel
import kotlinx.coroutines.delay
import java.util.Calendar

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override val mViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  HomeViewModel(repository = NoteRepository(applicationContext = context!!) ) as T
            }
        })[HomeViewModel::class.java]
    }

    override fun getLayoutRes() = R.layout.fragment_home

    @SuppressLint("NotifyDataSetChanged")
    override fun subscribeUi() {
        val adapter = HomeCalendarListAdapter(
            HomeCalendarListListener { v,vo,type ->
                when(type){
                    EventType.ON_BUTTON_CLICK -> {
                        println("${vo?.noteId} , $type")
                    }
                    else -> {

                    }
                }
            }
        )

        with(mViewModel){
//            loadValue()
            val now = Calendar.getInstance()
            mViewModel.dateClick(now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH))

            pickDataList.observe(viewLifecycleOwner){
                it?.let {
                    adapter.submitList(it)
                }

                if(it.isEmpty()){
                    mDataBinding.viewNoSearchData.root.visibility = View.VISIBLE
//                    mDataBinding.recyclerView.visibility = View.GONE
                } else {
//                    mDataBinding.recyclerView.visibility = View.VISIBLE
                    mDataBinding.viewNoSearchData.root.visibility = View.GONE
                }
            }
        }
        with(mDataBinding){
            vm = mViewModel
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            calendarView.setOnDateChangedListener { widget, date, selected ->
                mViewModel.dateClick(date.year, date.month, date.day)
            }

        }
    }

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}