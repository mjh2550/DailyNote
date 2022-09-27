package com.android.dailynote.base

import com.android.dailynote.data.model.response.ResponseResult

/**
 * Created by KimBH on 2022/07/12.
 */
interface BaseContractViewModel {
    fun observeResultCode(resResult: ResponseResult?)
    fun observeErrorMessage(e: Throwable)
}