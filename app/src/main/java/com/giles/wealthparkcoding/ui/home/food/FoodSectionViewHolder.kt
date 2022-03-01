package com.giles.wealthparkcoding.ui.home.food

import com.giles.wealthparkcoding.data.model.home.CitySection
import com.giles.wealthparkcoding.data.model.home.FoodSection
import com.giles.wealthparkcoding.data.model.home.HomeSection
import com.giles.wealthparkcoding.databinding.FoodSectionBinding
import com.giles.wealthparkcoding.ui.home.HomeViewModel
import com.giles.wealthparkcoding.ui.home.city.CitySectionAdapter
import com.giles.wealthparkcoding.ui.home.common.HomeViewHolder

class FoodSectionViewHolder (
    binding: FoodSectionBinding,
    val viewModel: HomeViewModel,
) : HomeViewHolder<FoodSectionBinding>(binding) {
    private val listAdapter = FoodSectionAdapter(viewModel)

    init {
        binding.recyclerView.apply {
            adapter = listAdapter
        }
    }

    override fun bind(section: HomeSection, position: Int) {
        (section as FoodSection).let {
            binding.apply {
                foodSection = it
                this.viewModel = this@FoodSectionViewHolder.viewModel
                executePendingBindings()
            }
        }
    }

}