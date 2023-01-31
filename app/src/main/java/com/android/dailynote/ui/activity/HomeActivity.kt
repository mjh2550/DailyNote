package com.android.dailynote.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.base.BaseViewModel
import com.android.dailynote.databinding.ActivityHomeBinding
import com.android.dailynote.ui.viewmodel.HomeViewModel
import com.android.dailynote.ui.viewmodel.MainViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding,MainViewModel>() {
    lateinit var navController: NavController
    lateinit var navHostFragment : NavHostFragment

    override fun getLayoutRes() = R.layout.activity_home

    override val mViewModel = MainViewModel()

    override fun subscribeUi() {
        with(mViewModel){

        }
        with(mDataBinding){

        }
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        mDataBinding.bottomNavigationView.setupWithNavController(navController)
    }
}
