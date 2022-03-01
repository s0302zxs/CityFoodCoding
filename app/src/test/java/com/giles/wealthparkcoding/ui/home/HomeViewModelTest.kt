package com.giles.wealthparkcoding.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.giles.wealthparkcoding.CITY_TOKEN
import com.giles.wealthparkcoding.FOOD_TOKEN
import com.giles.wealthparkcoding.MainCoroutineRule
import com.giles.wealthparkcoding.data.model.ApiResult
import com.giles.wealthparkcoding.data.model.MockData
import com.giles.wealthparkcoding.data.model.home.CitySection
import com.giles.wealthparkcoding.data.model.home.FoodSection
import com.giles.wealthparkcoding.data.repository.MockInfoRepository
import com.giles.wealthparkcoding.exception.WealthParkException
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    // Subject under test
    private lateinit var homeViewModel: HomeViewModel

    // Use a mock repository to be injected into the viewmodel
    private lateinit var infoRepository: MockInfoRepository

    // Set the main coroutines dispatcher for unit testing.
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        infoRepository = MockInfoRepository()
        homeViewModel = HomeViewModel(infoRepository)
    }

    @Test
    fun testGetCitiesFoodSuccess() {
        infoRepository.citiesResponse = ApiResult.Success(MockData.MOCK_CITIES_LIST)
        infoRepository.foodsResponse = ApiResult.Success(MockData.MOCK_FOODS_LIST)
        homeViewModel.getCitiesFoods(CITY_TOKEN, FOOD_TOKEN)

        Assert.assertEquals(2, homeViewModel.items.value?.size)
        Assert.assertEquals(3, (homeViewModel.items.value?.get(0) as CitySection).cities.size)
        Assert.assertEquals(4, (homeViewModel.items.value?.get(1) as FoodSection).foods.size)
    }

    @Test
    fun testGetCitiesFoodBothEmpty() {
        infoRepository.citiesResponse = ApiResult.Success(emptyList())
        infoRepository.foodsResponse = ApiResult.Success(emptyList())
        homeViewModel.getCitiesFoods(CITY_TOKEN, FOOD_TOKEN)

        Assert.assertEquals(2, homeViewModel.items.value?.size)
        Assert.assertEquals(0, (homeViewModel.items.value?.get(0) as CitySection).cities.size)
        Assert.assertEquals(0, (homeViewModel.items.value?.get(1) as FoodSection).foods.size)
    }

    @Test
    fun testGetCitiesFoodBothError() {
        infoRepository.citiesResponse = ApiResult.Error(WealthParkException(message = "cityError"))
        infoRepository.foodsResponse = ApiResult.Error(WealthParkException(message = "foodError"))
        homeViewModel.getCitiesFoods(CITY_TOKEN, FOOD_TOKEN)

        Assert.assertEquals(0, homeViewModel.items.value?.size)
        Assert.assertEquals("cityErrorfoodError", homeViewModel.errorMessage.value)
    }

    @Test
    fun testGetCitiesFoodCitySuccessFoodFail() {
        infoRepository.citiesResponse = ApiResult.Success(MockData.MOCK_CITIES_LIST)
        infoRepository.foodsResponse = ApiResult.Error(WealthParkException(message = "foodError"))
        homeViewModel.getCitiesFoods(CITY_TOKEN, FOOD_TOKEN)

        Assert.assertEquals(1, homeViewModel.items.value?.size)
        Assert.assertEquals(3, (homeViewModel.items.value?.get(0) as CitySection).cities.size)
    }

    @Test
    fun testGetCitiesFoodFoodSuccessCityFail() {
        infoRepository.citiesResponse = ApiResult.Error(WealthParkException(message = "cityError"))
        infoRepository.foodsResponse = ApiResult.Success(MockData.MOCK_FOODS_LIST)
        homeViewModel.getCitiesFoods(CITY_TOKEN, FOOD_TOKEN)

        Assert.assertEquals(1, homeViewModel.items.value?.size)
        Assert.assertEquals(4, (homeViewModel.items.value?.get(0) as FoodSection).foods.size)
    }

}