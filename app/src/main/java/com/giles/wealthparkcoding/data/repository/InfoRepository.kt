package com.giles.wealthparkcoding.data.repository

import com.giles.wealthparkcoding.data.model.ApiResult
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import kotlinx.coroutines.flow.Flow

interface InfoRepository {
    fun getCities(token : String) : Flow<ApiResult<List<City>>>
    fun getFoods(token : String) : Flow<ApiResult<List<Food>>>
    suspend fun insertCities(cities: List<City>)
    suspend fun insertFoods(foods: List<Food>)
}