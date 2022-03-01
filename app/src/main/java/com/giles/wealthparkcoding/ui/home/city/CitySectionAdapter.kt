package com.giles.wealthparkcoding.ui.home.city

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.giles.wealthparkcoding.data.model.City
import com.giles.wealthparkcoding.databinding.CitySectionItemBinding
import com.giles.wealthparkcoding.ui.home.HomeViewModel

class CitySectionAdapter (private val viewModel: HomeViewModel) :
    ListAdapter<City, RecyclerView.ViewHolder>(CitySectionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = CitySectionItemBinding.inflate(layoutInflater, parent, false)
        return CityViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as CityViewHolder).bind(
            viewModel,
            item
        )
    }

}


class CitySectionDiffCallback : DiffUtil.ItemCallback<City>() {
    override fun areItemsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: City,
        newItem: City
    ): Boolean {
        //force to update now, need to fix
        return oldItem == newItem
    }
}