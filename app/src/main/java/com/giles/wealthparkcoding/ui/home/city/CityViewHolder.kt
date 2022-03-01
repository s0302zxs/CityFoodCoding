package com.giles.wealthparkcoding.ui.home.city

import androidx.recyclerview.widget.RecyclerView
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.databinding.CitySectionItemBinding
import com.giles.wealthparkcoding.ui.home.HomeViewModel

class CityViewHolder(private val binding: CitySectionItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        viewModel: HomeViewModel,
        item: City,
    ) {
        binding.viewModel = viewModel
        binding.city = item
        binding.executePendingBindings()
    }
}
