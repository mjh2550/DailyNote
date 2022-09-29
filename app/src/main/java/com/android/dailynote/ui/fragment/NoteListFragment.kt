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

class NoteListFragment : BaseFragment<FragmentNoteListBinding,NoteListViewModel>() {
    override val mViewModel: NoteListViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_note_list

    override fun subscribeUi() {
        with(mViewModel){

        }
        with(mDataBinding){

            //TODO TEST LIST
            val dateAndtime: LocalDateTime = LocalDateTime.now()
            val testVO = NoteVO(
                1,
                "testTitle",
                "empty contents",
                "test Writer",
                "Y",
                null,
                null,
                dateAndtime,
                "Y",
                null,
                null
            )
            val testVO2 = NoteVO(
                2,
                "testTitle2",
                "empty contents",
                "test Writer",
                "Y",
                null,
                null,
                dateAndtime,
                "Y",
                null,
                null
            )
            val testVO3 = NoteVO(
                3,
                "testTitle3",
                "empty contents",
                "test Writer",
                "Y",
                null,
                null,
                dateAndtime,
                "Y",
                null,
                null
            )
            val list = arrayListOf(testVO,testVO2,testVO3)


            lvNoteItem.apply{
                adapter = NoteListAdapter(requireContext(),list)
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