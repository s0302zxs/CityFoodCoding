package com.giles.wealthparkcoding.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.giles.wealthparkcoding.databinding.DetailFragmentBinding
import timber.log.Timber
import java.util.*

class DetailFragment : Fragment() {
    private lateinit var viewDataBinding: DetailFragmentBinding
    private val viewModel by viewModels<DetailViewModel>()
    private var loopTimer: Timer? = null
    private val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DetailFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = this@DetailFragment.viewModel

        }
        viewModel.apply {
            imgUrl = args.imgUrl
            name = args.name
            description = args.description
        }

        return viewDataBinding.root
    }

}