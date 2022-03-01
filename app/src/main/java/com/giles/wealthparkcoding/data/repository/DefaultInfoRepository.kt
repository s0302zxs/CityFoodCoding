package com.giles.wealthparkcoding.data.repository

import com.giles.wealthparkcoding.data.dataSource.RemoteDataSource
import com.giles.wealthparkcoding.data.dataSource.defaultRemoteDataSourceInstance
import com.giles.wealthparkcoding.data.dataSource.local.LocalDataSource
import com.giles.wealthparkcoding.data.dataSource.local.defaultLocalDataSourceInstance
import com.giles.wealthparkcoding.data.model.ApiResult
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import timber.log.Timber


class DefaultInfoRepository(
    private val remoteDataSourceInstance: RemoteDataSource = defaultRemoteDataSourceInstance,
    private val localDataSourceInstance: LocalDataSource = defaultLocalDataSourceInstance,
) : InfoRepository {
    override fun getCities(token: String): Flow<ApiResult<List<City>>> {
        return remoteDataSourceInstance.getCities(token).map { it ->
            when (it.isSuccessful) {
                true -> {
                    it.body()?.let {

                        //delete DB
                        localDataSourceInstance.deleteCities()
                        //save to DB
                        localDataSourceInstance.insertCities(it)

                        //do sorting
                        val sortedCities = it.sortedBy { city ->
                            city.name
                        }

                        ApiResult.Success(sortedCities)


                    } ?: run {
                        var localCity: List<City> = localDataSourceInstance.getCities()
                        ApiResult.Success(localCity)
                    }
                }
                else -> {
                    Timber.d("http error")
                    var localCity: List<City> = localDataSourceInstance.getCities()
                    ApiResult.Success(localCity)
                }
            }
        }

    }


    override fun getFoods(token: String): Flow<ApiResult<List<Food>>> {
        return remoteDataSourceInstance.getFoods(token).map { it ->
            Timber.d("getFoods it.isSuccessful = ${it.isSuccessful}")
            when (it.isSuccessful) {
                true -> {
                    it.body()?.let {
                        //delete DB
                        localDataSourceInstance.deleteFoods()
                        //save to DB
                        localDataSourceInstance.insertFoods(it)

                        //do sorting
                        val sortedFoods = it.sortedBy { food ->
                            food.name
                        }

                        ApiResult.Success(sortedFoods)

                    } ?: run {
                        var localFoods: List<Food> = localDataSourceInstance.getFoods()
                        ApiResult.Success(localFoods)
                    }
                }
                else -> {
                    var localFoods: List<Food> = localDataSourceInstance.getFoods()
                    ApiResult.Success(localFoods)
                }
            }
        }
    }

    /** No Use, just create the interface */
    override suspend fun insertCities(cities: List<City>) {
        return withContext(Dispatchers.IO) {
            return@withContext localDataSourceInstance.insertCities(cities)
        }
    }

    /** No Use, just create the interface */
    override suspend fun insertFoods(foods: List<Food>) {
        return withContext(Dispatchers.IO) {
            return@withContext localDataSourceInstance.insertFoods(foods)
        }
    }

}

val defaultInfoRepositoryInstance get() = DefaultInfoRepository()