package com.giles.wealthparkcoding.api.httpClient

import com.giles.wealthparkcoding.api.interceptor.httpLoggingInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

const val API_TIMEOUT_MILLISECONDS = 20000L

fun createClient() = OkHttpClient.Builder()
    .connectTimeout(API_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
    .readTimeout(API_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
    .writeTimeout(API_TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
    .addInterceptor(httpLoggingInterceptor)
    .build()
