package com.giles.wealthparkcoding.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giles.wealthparkcoding.SingleLiveEvent
import com.giles.wealthparkcoding.data.model.ApiResult
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import com.giles.wealthparkcoding.data.model.home.CitySection
import com.giles.wealthparkcoding.data.model.home.FoodSection
import com.giles.wealthparkcoding.data.model.home.HomeSection
import com.giles.wealthparkcoding.data.repository.InfoRepository
import com.giles.wealthparkcoding.data.repository.defaultInfoRepositoryInstance
import com.giles.wealthparkcoding.exception.WealthParkException
import com.giles.wealthparkcoding.ui.home.HomeAdapter.Companion.TYPE_CITY
import com.giles.wealthparkcoding.ui.home.HomeAdapter.Companion.TYPE_FOOD
import kotlinx.coroutines.flow.*
import timber.log.Timber

class HomeViewModel(
    private val infoRepository: InfoRepository = defaultInfoRepositoryInstance,
) : ViewModel() {

    private val _items =
        SingleLiveEvent<MutableList<HomeSection>>().apply { value = mutableListOf() }
    val items: LiveData<MutableList<HomeSection>> = _items

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isShowRetry = MutableLiveData(false)
    val isShowRetry: LiveData<Boolean> = _isShowRetry
    private val _errorMessage = MutableLiveData<String>("")
    val errorMessage: LiveData<String> = _errorMessage

    lateinit var navigateToDetailPage: (imageUrl: String?, name: String?, description: String?) -> Unit
    var onRetry: (() -> Unit) = {}

    fun getCitiesFoods(cityToken: String, foodToken: String) {
        onRetry = { getCitiesFoods(cityToken, foodToken) }
        _isLoading.value = true
        _isShowRetry.value = false
        combine(
            infoRepository.getCities(cityToken),
            infoRepository.getFoods(foodToken)
        ) { cityResult, foodResult ->
            var sections: MutableList<HomeSection> = mutableListOf()
            var errorMessage: String? = ""
            when (cityResult) {
                is ApiResult.Success<List<City>> -> {

                    var citySection = CitySection(
                        type = TYPE_CITY,
                        cities = cityResult.data
                    )
                    sections.add(citySection)

                }
                is ApiResult.Error -> {
                    errorMessage += cityResult.exception.message
                }
            }

            when (foodResult) {
                is ApiResult.Success<List<Food>> -> {

                    var foodSection = FoodSection(
                        type = TYPE_FOOD,
                        foods = foodResult.data
                    )
                    sections.add(foodSection)

                }
                is ApiResult.Error -> {
                    errorMessage += foodResult.exception.message
                }
            }

            if (sections.size == 0) {
                throw WealthParkException(errorMessage)
            }
            _items.value = sections

        }.catch {
            _errorMessage.value = it.message
            _isShowRetry.value = true
            _items.value = mutableListOf()
        }.onCompletion {
            _isLoading.value = false
        }.launchIn(viewModelScope)

    }

    val onOpenDetailPageByCity: (city: City) -> Unit = { it ->
        navigateToDetailPage(it.image, it.name, it.description)
    }

    val onOpenDetailPageByFood: (food: Food) -> Unit = { it ->
        navigateToDetailPage(it.image, it.name, null)
    }

}