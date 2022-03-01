package com.giles.wealthparkcoding.data.dataSource.local

import com.giles.wealthparkcoding.WealthParkCodingApp
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import com.giles.wealthparkcoding.model.CityFoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultLocalDataSource(
    private val cityFoodDao: CityFoodDao = WealthParkCodingApp.wealthParkDatabase.cityFoodDao()
) : LocalDataSource {
    override suspend fun getCities(): List<City> {
        return withContext(Dispatchers.IO){
            cityFoodDao.getCities()
        }
    }

    override suspend fun getFoods(): List<Food> {
        return withContext(Dispatchers.IO){
            cityFoodDao.getFoods()
        }
    }

    override suspend fun insertCities(cities: List<City>) {
        return withContext(Dispatchers.IO){
            cityFoodDao.insertCities(cities)
        }
    }

    override suspend fun insertFoods(foods: List<Food>) {
        return withContext(Dispatchers.IO){
            cityFoodDao.insertFoods(foods)
        }
    }

    override suspend fun deleteCities() {
        return withContext(Dispatchers.IO){
            cityFoodDao.deleteCities()
        }
    }

    override suspend fun deleteFoods() {
        return withContext(Dispatchers.IO){
            cityFoodDao.deleteFoods()
        }
    }
}

val defaultLocalDataSourceInstance: LocalDataSource get() = DefaultLocalDataSource()