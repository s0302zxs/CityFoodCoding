package com.giles.wealthparkcoding.data.dataSource

import com.giles.wealthparkcoding.api.ApiService
import com.giles.wealthparkcoding.api.defaultApiService
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class DefaultRemoteDataSource (private val apiService: ApiService = defaultApiService,) : RemoteDataSource {
    override fun getCities(token: String) : Flow<Response<List<City>>> {
        return apiService.getCities(token)
    }

    override fun getFoods(token: String): Flow<Response<List<Food>>> {
        return apiService.getFoods(token)
    }
}

val defaultRemoteDataSourceInstance: RemoteDataSource get() = DefaultRemoteDataSource()
