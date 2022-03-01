package com.giles.wealthparkcoding.data.dataSource

import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RemoteDataSource {
    fun getCities(token : String) : Flow<Response<List<City>>>
    fun getFoods(token : String) : Flow<Response<List<Food>>>
}
