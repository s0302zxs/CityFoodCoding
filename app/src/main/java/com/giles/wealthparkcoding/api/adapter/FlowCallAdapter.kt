package com.giles.wealthparkcoding.api.adapter

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class ResponseCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, Flow<Response<R>>> {

    override fun responseType() = responseType

    @ExperimentalCoroutinesApi
    override fun adapt(call: Call<R>): Flow<Response<R>> {
        return callbackFlow {
            val started = AtomicBoolean(false)
            if (started.compareAndSet(false, true)) {
                call.enqueue(object : Callback<R> {
                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        offer(response)
                        close()
                    }

                    override fun onFailure(call: Call<R>, throwable: Throwable) {
                        close(throwable)
                    }
                })
            }
            awaitClose { call.cancel() }
        }
    }
}

class BodyCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, Flow<R>> {

    override fun responseType() = responseType

    @ExperimentalCoroutinesApi
    override fun adapt(call: Call<R>): Flow<R> {
        return callbackFlow {
            val started = AtomicBoolean(false)
            if (started.compareAndSet(false, true)) {
                call.enqueue(object : Callback<R> {
                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        response.body()?.let { offer(it) }
                        close()
                    }

                    override fun onFailure(call: Call<R>, throwable: Throwable) {
                        close(throwable)
                    }
                })
            }
            awaitClose { call.cancel() }
        }
    }
}
