package com.android.dailynote.ui.activity

import androidx.activity.viewModels
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