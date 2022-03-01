package com.giles.wealthparkcoding.ui.detail

import androidx.lifecycle.ViewModel
import com.giles.wealthparkcoding.data.repository.InfoRepository
import com.giles.wealthparkcoding.data.repository.defaultInfoRepositoryInstance

class DetailViewModel (
    private val infoRepository: InfoRepository = defaultInfoRepositoryInstance,
) : ViewModel() {
    var imgUrl: String? = null
    var name: String? = null
    var description: String? = null

}