package com.giles.wealthparkcoding.data.dataSource.local

import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food

interface LocalDataSource {
    suspend fun getCities() : List<City>
    suspend fun getFoods() : List<Food>
    suspend fun insertCities(cities : List<City>)
    suspend fun insertFoods(foods : List<Food>)
    suspend fun deleteCities()
    suspend fun deleteFoods()
}
