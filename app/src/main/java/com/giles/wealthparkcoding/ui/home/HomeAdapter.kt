package com.giles.wealthparkcoding.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.giles.wealthparkcoding.R
import com.giles.wealthparkcoding.data.model.home.CitySection
import com.giles.wealthparkcoding.data.model.home.FoodSection
import com.giles.wealthparkcoding.data.model.home.HomeSection
import com.giles.wealthparkcoding.databinding.CitySectionBinding
import com.giles.wealthparkcoding.databinding.FoodSectionBinding
import com.giles.wealthparkcoding.ui.home.city.CitySectionViewHolder
import com.giles.wealthparkcoding.ui.home.common.HomeViewHolder
import com.giles.wealthparkcoding.ui.home.food.FoodSectionViewHolder
import timber.log.Timber

class HomeAdapter (
    private val viewModel: HomeViewModel,
) : ListAdapter<HomeSection, HomeViewHolder<ViewDataBinding>>(
    HomeSectionDiffCallback()
) {
    //define it if data come from api
    companion object {
        const val TYPE_CITY = "city"
        const val TYPE_FOOD = "food"
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is CitySection -> R.layout.city_section
            is FoodSection -> R.layout.food_section
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeViewHolder<ViewDataBinding> {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.city_section -> {
                val binding = CitySectionBinding.inflate(layoutInflater, parent, false)
                CitySectionViewHolder(
                    binding,
                    viewModel
                ) as HomeViewHolder<ViewDataBinding>
            }
            R.layout.food_section -> {
                val binding = FoodSectionBinding.inflate(layoutInflater, parent, false)
                FoodSectionViewHolder(
                    binding,
                    viewModel
                ) as HomeViewHolder<ViewDataBinding>
            }
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder<ViewDataBinding>, position: Int) {
        holder.bind(getItem(position), position)
    }
}


class HomeSectionDiffCallback :
    DiffUtil.ItemCallback<HomeSection>() {

    override fun areItemsTheSame(
        oldItem: HomeSection,
        newItem: HomeSection
    ): Boolean {
        val oldName = oldItem.javaClass.name
        val newName = newItem.javaClass.name
        return oldName == newName
    }

    override fun areContentsTheSame(
        oldItem: HomeSection,
        newItem: HomeSection
    ): Boolean {
        return oldItem == newItem
    }

}