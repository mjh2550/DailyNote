package com.android.dailynote.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.dailynote.R
import com.android.dailynote.base.BaseFragment
import com.android.dailynote.databinding.FragmentEtcBinding
import com.android.dailynote.ui.viewmodel.MainViewModel

class EtcFragment : Fragment(){
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_etc, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        val titleText = activity?.findViewById(R.id.title_text) as TextView
        titleText.text = "환경설정"
    }
}