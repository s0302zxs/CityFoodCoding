package com.giles.wealthparkcoding.ui.home.food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giles.wealthparkcoding.data.model.Food
import com.giles.wealthparkcoding.databinding.FoodSectionItemBinding
import com.giles.wealthparkcoding.ui.home.HomeViewModel

class FoodSectionAdapter (private val viewModel: HomeViewModel) :
    ListAdapter<Food, RecyclerView.ViewHolder>(FoodSectionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = FoodSectionItemBinding.inflate(layoutInflater, parent, false)
        return FoodViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as FoodViewHolder).bind(
            viewModel,
            item
        )
    }

}


class FoodSectionDiffCallback : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(
        oldItem: Food,
        newItem: Food
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Food,
        newItem: Food
    ): Boolean {
        //force to update now, need to fix
        return oldItem == newItem
    }
}
