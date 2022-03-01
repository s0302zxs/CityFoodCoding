package com.giles.wealthparkcoding.ui.home.city

import com.giles.wealthparkcoding.data.model.home.CitySection
import com.giles.wealthparkcoding.data.model.home.HomeSection
import com.giles.wealthparkcoding.databinding.CitySectionBinding
import com.giles.wealthparkcoding.ui.home.HomeViewModel
import com.giles.wealthparkcoding.ui.home.common.HomeViewHolder

class CitySectionViewHolder (
    binding: CitySectionBinding,
    val viewModel: HomeViewModel,
) : HomeViewHolder<CitySectionBinding>(binding) {
    private val listAdapter = CitySectionAdapter(viewModel)

    init {
        binding.recyclerView.apply {
            adapter = listAdapter
        }
    }

    override fun bind(section: HomeSection, position: Int) {
        (section as CitySection).let {
            binding.apply {
                citySection = it
                this.viewModel = this@CitySectionViewHolder.viewModel
                executePendingBindings()
            }
        }
    }

}