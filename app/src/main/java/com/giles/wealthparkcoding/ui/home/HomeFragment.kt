package com.giles.wealthparkcoding.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.giles.wealthparkcoding.CITY_TOKEN
import com.giles.wealthparkcoding.FOOD_TOKEN
import com.giles.wealthparkcoding.databinding.HomeFragmentBinding
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var viewDataBinding: HomeFragmentBinding
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = HomeFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = this@HomeFragment.viewModel
            recyclerView.adapter = HomeAdapter(this@HomeFragment.viewModel)
        }
        viewModel.apply {
            navigateToDetailPage = { imageUrl, name, description ->

                NavHostFragment.findNavController(this@HomeFragment).navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        imgUrl = imageUrl,
                        name = name,
                        description = description
                    )

                )
            }

        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewModel.getCitiesFoods(CITY_TOKEN, FOOD_TOKEN)
    }
}