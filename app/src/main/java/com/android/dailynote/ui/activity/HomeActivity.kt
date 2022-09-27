package com.android.dailynote.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.databinding.ActivityHomeBinding
import com.android.dailynote.ui.viewmodel.HomeViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>() {
    override fun getLayoutRes() = R.layout.activity_home

    override val mViewModel : HomeViewModel by viewModels()

    override fun subscribeUi() {
        with(mViewModel){

        }
        with(mDataBinding){
            vm = mViewModel
        }
    }
}
