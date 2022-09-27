package com.android.dailynote.base

import com.android.dailynote.data.model.response.ResponseResult

/**
 * Created by KimBH on 2022/07/12.
 */
interface BaseContractView {
    fun subscribeUi()
    fun getLayoutRes(): Int
    fun loadResultCode(resResult: ResponseResult?)
    fun loadErrorMessage(e: Throwable)
}