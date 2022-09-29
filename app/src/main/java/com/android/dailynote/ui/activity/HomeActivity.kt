package com.android.dailynote.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.android.dailynote.R
import com.android.dailynote.base.BaseActivity
import com.android.dailynote.databinding.ActivityHomeBinding
import com.android.dailynote.ui.viewmodel.HomeViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding,HomeViewModel>() {
    lateinit var navController: NavController
    lateinit var navHostFragment : NavHostFragment

    override fun getLayoutRes() = R.layout.activity_home

    override val mViewModel : HomeViewModel by viewModels()

    override fun subscribeUi() {
        with(mViewModel){

        }
        with(mDataBinding){
            vm = mViewModel
        }
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        mDataBinding.bottomNavigationView.setupWithNavController(navController)
    }
}
