package com.giles.wealthparkcoding.api

import com.giles.wealthparkcoding.api.adapter.FlowCallAdapterFactory
import com.giles.wealthparkcoding.api.converter.defaultGsonConverterFactory
import com.giles.wealthparkcoding.api.httpClient.createClient
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.npoint.io/"

interface ApiService {
    @GET("/{token}")
    fun getCities(@Path("token") token: String): Flow<Response<List<City>>>

    @GET("/{token}")
    fun getFoods(@Path("token") token: String): Flow<Response<List<Food>>>

}

val defaultApiService: ApiService
    get() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(defaultGsonConverterFactory)
//        .addCallAdapterFactory(FlowCallAdapterFactory())
//        .addCallAdapterFactory(FlowCallAdapterFactory())
        .addCallAdapterFactory(FlowCallAdapterFactory())
        .client(createClient())
        .build()
        .create(ApiService::class.java)

