package com.giles.wealthparkcoding.data.model.home

import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food

sealed class HomeSection {
}

data class CitySection (
    val type: String,
    val cities : List<City>
) : HomeSection()

data class FoodSection (
    val type: String,
    val foods : List<Food>
) : HomeSection()