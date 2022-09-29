package com.android.dailynote.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.dailynote.R
import com.android.dailynote.base.BaseFragment
import com.android.dailynote.databinding.FragmentNoteListBinding
import com.android.dailynote.ui.viewmodel.NoteListViewModel

class NoteListFragment : BaseFragment<FragmentNoteListBinding,NoteListViewModel>() {
    override val mViewModel: NoteListViewModel by activityViewModels()

    override fun getLayoutRes() = R.layout.fragment_note_list

    override fun subscribeUi() {
        with(mViewModel){

        }
        with(mDataBinding){

        }
    }
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
}