package com.giles.wealthparkcoding.ui.home.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.giles.wealthparkcoding.data.model.home.HomeSection

abstract class HomeViewHolder<V : ViewDataBinding>(val binding: V) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(section: HomeSection, position: Int)

}