package com.giles.wealthparkcoding.data.dataSource.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.giles.wealthparkcoding.WealthParkCodingAppDatabase
import com.giles.wealthparkcoding.data.model.MockData
import com.giles.wealthparkcoding.model.CityFoodDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class DefaultLocalDataSourceTest {

    private lateinit var cityFoodDao: CityFoodDao
    private lateinit var db: WealthParkCodingAppDatabase

    // Set the main coroutines dispatcher for unit testing.
//    @get:Rule
//    val mainCoroutineRule = MainCoroutineRule()
//
//    // Executes each task synchronously using Architecture Components.
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(
            appContext, WealthParkCodingAppDatabase::class.java).build()
        cityFoodDao = db.cityFoodDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun testGetCitiesAndInsertCities() {
        runBlocking{
            cityFoodDao.insertCities(MockData.MOCK_CITIES_LIST)
            val listLocal = cityFoodDao.getCities()
            assertEquals(MockData.MOCK_CITIES_LIST.size, listLocal.size)
        }
    }

    @Test
    fun testGetFoodsAndInsertFoods() {
        runBlocking{
            cityFoodDao.insertFoods(MockData.MOCK_FOODS_LIST)
            val listLocal = cityFoodDao.getFoods()
            assertEquals(MockData.MOCK_FOODS_LIST.size, listLocal.size)
        }
    }

    @Test
    fun testDeleteCities() {
        runBlocking{
            cityFoodDao.insertCities(MockData.MOCK_CITIES_LIST)
            cityFoodDao.deleteCities()
            val listLocal = cityFoodDao.getCities()
            assertEquals(0, listLocal.size)
        }
    }

    @Test
    fun testDeleteFoods() {
        runBlocking{
            cityFoodDao.insertFoods(MockData.MOCK_FOODS_LIST)
            cityFoodDao.deleteFoods()
            val listLocal = cityFoodDao.getFoods()
            assertEquals(MockData.MOCK_FOODS_LIST.size, listLocal.size)
        }
    }

}