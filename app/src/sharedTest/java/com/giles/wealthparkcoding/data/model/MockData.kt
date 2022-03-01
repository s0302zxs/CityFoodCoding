package com.giles.wealthparkcoding.data.model

import com.giles.wealthparkcoding.data.model.home.CitySection
import com.giles.wealthparkcoding.data.model.home.FoodSection
import com.giles.wealthparkcoding.ui.home.HomeAdapter.Companion.TYPE_CITY
import com.giles.wealthparkcoding.ui.home.HomeAdapter.Companion.TYPE_FOOD

object MockData {
    val MOCK_CITY_SECTION_RESPONSE : CitySection by lazy {
        CitySection(
            type = TYPE_CITY,
            cities = MOCK_CITIES_LIST
            )
    }

    val MOCK_FOOD_SECTION_RESPONSE : FoodSection by lazy {
        FoodSection(
            type = TYPE_FOOD,
            foods = MOCK_FOODS_LIST
        )
    }

    val MOCK_CITIES_LIST : List<City> by lazy {
        listOf(
            City(
                name = "Nara",
                description = "Nara Description",
                image = "Nara image"
            ),
            City(
                name = "Osaka",
                description = "Osaka Description",
                image = "Osaka image"
            ),
            City(
                name = "Tokyo",
                description = "Tokyo Description",
                image = "Tokyo image"
            ),
        )
    }

    val MOCK_FOODS_LIST : List<Food> by lazy {
        listOf(
            Food(
                name = "Sushi",
                image = "Sushi image"
            ),
            Food(
                name = "Ramen",
                image = "Ramen image"
            ),
            Food(
                name = "TestFood1",
                image = "TestFood1 image"
            ),
            Food(
                name = "TestFood2",
                image = "TestFood2 image"
            ),
        )
    }
}