package com.giles.wealthparkcoding.ui.home.food

import androidx.recyclerview.widget.RecyclerView
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.data.model.Food
import com.giles.wealthparkcoding.databinding.CitySectionItemBinding
import com.giles.wealthparkcoding.databinding.FoodSectionItemBinding
import com.giles.wealthparkcoding.ui.home.HomeViewModel

class FoodViewHolder (private val binding: FoodSectionItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        viewModel: HomeViewModel,
        item: Food,
    ) {
        binding.viewModel = viewModel
        binding.food = item
        binding.executePendingBindings()
    }
}