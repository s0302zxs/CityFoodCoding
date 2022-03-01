package com.giles.wealthparkcoding.data.dataSource.local

import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food

class MockLocalDataSource : LocalDataSource{
    override suspend fun getCities(): List<City> {
//        return emptyList()
        TODO("Not yet implemented")
    }

    override suspend fun getFoods(): List<Food> {
        TODO("Not yet implemented")
    }

    override suspend fun insertCities(cities: List<City>) {

    }

    override suspend fun insertFoods(foods: List<Food>) {

    }

    override suspend fun deleteCities() {

    }

    override suspend fun deleteFoods() {

    }
}