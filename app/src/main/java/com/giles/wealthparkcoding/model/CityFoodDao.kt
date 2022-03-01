package com.giles.wealthparkcoding.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food

@Dao
interface CityFoodDao {
    @Query("SELECT * FROM City ORDER BY name ASC")
    suspend fun getCities(): List<City>

    @Query("SELECT * FROM Food ORDER BY name ASC")
    suspend fun getFoods(): List<Food>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities : List<City>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoods(foods : List<Food>)

    @Query("DELETE FROM City")
    suspend fun deleteCities()

    @Query("DELETE FROM Food")
    suspend fun deleteFoods()

}