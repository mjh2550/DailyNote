package com.android.dailynote.base

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.dailynote.data.model.response.ResponseResult
/**
 * Created by KimBH on 2022/07/12.
 */
abstract class BaseViewModel() : ViewModel(), BaseContractViewModel {
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    var resReuslt: MutableLiveData<ResponseResult> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    override fun observeResultCode(resResult: ResponseResult?) {
        isLoading.postValue(false)
        resResult?.let {
            resReuslt.postValue(it)
        }
    }

    override fun observeErrorMessage(e: Throwable) {
        isLoading.postValue(false)
        error.postValue(e)
    }
}