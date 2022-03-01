package com.giles.wealthparkcoding.data.repository

import com.giles.wealthparkcoding.data.model.ApiResult
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.lang.Exception

class MockInfoRepository : InfoRepository{
    var citiesResponse: ApiResult<List<City>> = ApiResult.Error(Exception())
    var foodsResponse: ApiResult<List<Food>> = ApiResult.Error(Exception())

    override fun getCities(token: String): Flow<ApiResult<List<City>>> {
        return flowOf(citiesResponse)
    }

    override fun getFoods(token: String): Flow<ApiResult<List<Food>>> {
        return flowOf(foodsResponse)
    }

    override suspend fun insertCities(cities: List<City>) {
        TODO("Not yet implemented")
    }

    override suspend fun insertFoods(foods: List<Food>) {
        TODO("Not yet implemented")
    }
}