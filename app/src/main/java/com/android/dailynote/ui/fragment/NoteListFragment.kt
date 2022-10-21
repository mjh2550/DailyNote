package com.android.dailynote.ui.fragment

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.dailynote.R
import com.android.dailynote.adapters.NoteListAdapter
import com.android.dailynote.adapters.NoteListListener
import com.android.dailynote.base.BaseFragment
import com.android.dailynote.data.model.entity.NoteVO
import com.android.dailynote.data.model.roomdb.NoteRepository
import com.android.dailynote.databinding.FragmentNoteListBinding
import com.android.dailynote.ui.activity.HomeActivity
import com.android.dailynote.ui.activity.NoteWriteActivity
import com.android.dailynote.ui.viewmodel.NoteListViewModel
import com.android.dailynote.util.Util
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class NoteListFragment : BaseFragment<FragmentNoteListBinding,NoteListViewModel>() ,OnClickListener{

    override val mViewModel: NoteListViewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return  NoteListViewModel(repository = NoteRepository(applicationContext = context!!) ) as T
            }
        })[NoteListViewModel::class.java]
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        println("resultCode ${it.resultCode}")
        if(it.resultCode == Activity.RESULT_OK){
            val title = it.data?.getStringExtra("title")
            val contents = it.data?.getStringExtra("contents")

            val today = Date(System.currentTimeMillis()).toString()
            mViewModel.insertData(NoteVO(
                5,
                title!!,
                contents!!,
                "test Writer",
                "Y",
                null,
                null,
                today,
                "Y",
                null,
                null
            ))


        }
    }

    override fun getLayoutRes() = R.layout.fragment_note_list

    override fun subscribeUi() {

        val adapter = NoteListAdapter(NoteListListener { noteId ->
            Toast.makeText(requireContext(),"${noteId} 탭",Toast.LENGTH_SHORT).show()
        })

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
        }

    }
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_add -> onClickFloatingButton()
        }
    }

    private fun onClickFloatingButton (){

        val intent = Intent(requireActivity(),NoteWriteActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        startActivity(intent)
        startForResult.launch(intent)
    }

}