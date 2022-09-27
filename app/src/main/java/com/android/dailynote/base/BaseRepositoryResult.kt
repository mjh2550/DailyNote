package com.android.dailynote.base

import com.android.dailynote.data.model.response.ResponseResult

/**
 * Created by KimBH on 2022/07/12.
 */
sealed class BaseRepositoryResult<out R> {
    data class Data<out T>(val data: T?) : BaseRepositoryResult<T>()
    data class Result(val result: ResponseResult) : BaseRepositoryResult<Nothing>()
    data class Error(val throwable: Throwable) : BaseRepositoryResult<Nothing>()
}
