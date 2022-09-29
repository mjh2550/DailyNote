package com.android.dailynote.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.databinding.ActivityMainBinding
import com.android.dailynote.ui.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>() {

    override fun getLayoutRes() = R.layout.activity_main

    override val mViewModel: MainViewModel by viewModels()

    override fun subscribeUi() {
        with(mViewModel){

        }
        with(mDataBinding){
            vm = mViewModel
        }
    }
}