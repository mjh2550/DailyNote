package com.android.dailynote.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.dailynote.R
import com.android.dailynote.adapters.NoteListAdapter
import com.android.dailynote.base.BaseFragment
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.databinding.FragmentNoteListBinding
import com.android.dailynote.ui.viewmodel.NoteListViewModel
import com.android.dailynote.util.Util
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class NoteListFragment : BaseFragment<FragmentNoteListBinding,NoteListViewModel>() {

    private var dataList = ArrayList<NoteVO>()

    override val mViewModel: NoteListViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_note_list

    override fun subscribeUi() {
        with(mViewModel){
            dataList = list
        }
        with(mDataBinding){
            vm = mViewModel
            lvNoteItem.apply{
                adapter = NoteListAdapter(requireContext(),dataList)
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            }
        }

    }
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}