package com.giles.wealthparkcoding.data.repository

import com.giles.wealthparkcoding.data.dataSource.MockRemoteDataSource
import com.giles.wealthparkcoding.data.dataSource.local.LocalDataSource
import com.giles.wealthparkcoding.data.dataSource.local.MockLocalDataSource
import com.giles.wealthparkcoding.data.model.ApiResult
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DefaultInfoRepositoryTest {

    private lateinit var repository: DefaultInfoRepository

    private lateinit var remoteDataSource: MockRemoteDataSource
    private lateinit var localDataSource: MockLocalDataSource


    @Before
    fun setUp() {
        remoteDataSource =
            MockRemoteDataSource()

        localDataSource = MockLocalDataSource()

        repository =
            DefaultInfoRepository(
                remoteDataSourceInstance = remoteDataSource,
                localDataSourceInstance = localDataSource,
            )

        remoteDataSource.init()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCities() {
        remoteDataSource.loadApiResponseFile = "city.json"
        runBlocking {
            val flow = repository.getCities("token")
            flow.map {
                when(it){
                    is ApiResult.Success<List<City>> -> { 
                        Assert.assertEquals(4, it.data.size)
                    }
                    else -> {

                    }
                }
            }.collect()

        }
    }

    @Test
    fun getFoods() {
        remoteDataSource.loadApiResponseFile = "food.json"
        runBlocking {
            val flow = repository.getFoods("token")
            flow.map {
                when(it){
                    is ApiResult.Success<List<Food>> -> {
                        Assert.assertEquals(2, it.data.size)
                    }
                    else -> {

                    }
                }
            }.collect()

        }
    }
    /** TODO */
    @Test
    fun insertCities() {
    }

    /** TODO */
    @Test
    fun insertFoods() {
    }
}