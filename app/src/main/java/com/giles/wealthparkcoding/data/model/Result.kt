package com.giles.wealthparkcoding.data.model

sealed class ApiResult<out R> {

    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception) : ApiResult<Nothing>()

    /** TODO We can define other error type*/
    //EX :
    //data class Fail(): ApiResult<Nothing>()
}
