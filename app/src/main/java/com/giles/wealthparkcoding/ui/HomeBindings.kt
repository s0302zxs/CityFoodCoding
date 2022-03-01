package com.giles.wealthparkcoding.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import com.giles.wealthparkcoding.data.model.home.HomeSection
import com.giles.wealthparkcoding.ui.home.HomeAdapter
import com.giles.wealthparkcoding.ui.home.city.CitySectionAdapter
import com.giles.wealthparkcoding.ui.home.food.FoodSectionAdapter
import timber.log.Timber

object HomeBindings {
    @JvmStatic
    @BindingAdapter("homeItem")
    fun bindSubscribeRecyclerView(
        listView: RecyclerView,
        items: List<HomeSection>?
    ) {
        Timber.v(
            "homeItem , items.size = %d",
            items?.size
        )

        items?.apply {
            (listView.adapter as HomeAdapter).submitList(items)
            //check this
//            (listView.adapter as HomeAdapter).notifyDataSetChanged()
        }
    }

    @JvmStatic
    @BindingAdapter("citySectionItems")
    fun bindCitySectionItems(listView: RecyclerView, items: List<City>) {
        (listView.adapter as CitySectionAdapter).submitList(items)
    }

    @JvmStatic
    @BindingAdapter("foodSectionItems")
    fun bindFoodSectionItems(listView: RecyclerView, items: List<Food>) {
        (listView.adapter as FoodSectionAdapter).submitList(items)
    }

}