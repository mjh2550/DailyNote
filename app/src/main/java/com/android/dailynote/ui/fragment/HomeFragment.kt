package com.android.dailynote.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.dailynote.R
import com.android.dailynote.adapters.HomeCalendarListAdapter
import com.android.dailynote.adapters.HomeCalendarListListener
import com.android.dailynote.base.BaseFragment
import com.android.dailynote.common.EventType
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.FragmentHomeBinding
import com.android.dailynote.ui.viewmodel.HomeViewModel
import com.android.dailynote.ui.viewmodel.NoteListViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override val mViewModel: HomeViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  HomeViewModel(repository = NoteRepository(applicationContext = context!!) ) as T
            }
        })[HomeViewModel::class.java]
    }

    override fun getLayoutRes() = R.layout.fragment_home

    override fun subscribeUi() {
        val adapter = HomeCalendarListAdapter(
            HomeCalendarListListener { v,vo,type ->
                when(type){
                    EventType.ON_BUTTON_CLICK -> {
                        println("ON_BUTTON_CLICK")
                    }
                    else -> {

                    }
                }
            }
        )

        with(mViewModel){
            loadValue()
            pickDataList.observe(viewLifecycleOwner){
                it?.let {
                    adapter.submitList(it)
                }
            }
        }
        with(mDataBinding){
            vm = mViewModel
            recyclerView.adapter = adapter
            calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                mViewModel.dateClick(year, month, dayOfMonth)
            }

        }
    }

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}