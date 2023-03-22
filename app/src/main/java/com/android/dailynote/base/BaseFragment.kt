package com.android.dailynote.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.android.dailynote.data.model.response.ResponseResult
import com.android.dailynote.data.network.util.ErrorUtil
import com.android.dailynote.data.network.util.ResultCodeUtil
import com.android.dailynote.ui.loading.LoadingDialog

/**
 * Created by KimBH on 2022/07/12.
 */
abstract class BaseFragment<DB: ViewDataBinding, VM: BaseViewModel> : Fragment() , BaseContractView {
    protected lateinit var mDataBinding: DB
    abstract val mViewModel: VM

    fun isInitialized() = ::mDataBinding.isInitialized

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDataBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
        mDataBinding.lifecycleOwner = this.viewLifecycleOwner
//        mDataBinding.lifecycleOwner = this

        mViewModel.apply {
            isLoading.observe(viewLifecycleOwner) {
                if (it) {
                    LoadingDialog.shared().showLoading(requireActivity())
                } else {
                    LoadingDialog.shared().hideLoading()
                }
            }

            resResult.observe(viewLifecycleOwner) {
                loadResultCode(it)
            }

            error.observe(viewLifecycleOwner) {
                loadErrorMessage(it)
            }
        }

        subscribeUi()

        return mDataBinding.root
    }
    override fun loadResultCode(resResult: ResponseResult?) {
        ResultCodeUtil.setResult(requireActivity(), resResult)
    }

    override fun loadErrorMessage(e: Throwable) {
        ErrorUtil.showErrorMessage(requireActivity(), null) {}
    }

    abstract override fun getLayoutRes(): Int
//    abstract override fun subscribeUi()
}