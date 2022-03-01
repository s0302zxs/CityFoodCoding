package com.giles.wealthparkcoding.api.interceptor

import com.giles.wealthparkcoding.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor

val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
    .apply {
        this.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
    }
