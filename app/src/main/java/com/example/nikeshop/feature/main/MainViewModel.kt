package com.example.nikeshop.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.data.Banner
import com.example.nikeshop.data.Product
import com.example.nikeshop.data.SORT_LATEST
import com.example.nikeshop.data.SORT_POPULAR
import com.example.nikeshop.data.repository.BannerRepository
import com.example.nikeshop.data.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel(
    val productRepository: ProductRepository,
    val bannerRepository: BannerRepository
    ): NikeViewModel() {

    val productsLiveData = MutableLiveData<List<Product>>()
    val sliderLiveData = MutableLiveData<List<Banner>>()

    init {
        progressBarLiveData.value = true
        callGetProduct()
        callGetSlider()
    }

    private fun callGetProduct() = viewModelScope.launch {
        getProduct()
    }

    private fun callGetSlider() = viewModelScope.launch {
        getSlider()
    }

    private suspend fun getProduct(){
        productsLiveData.postValue(productRepository.getProducts(SORT_POPULAR))
        progressBarLiveData.postValue(false)
    }

    private suspend fun getSlider(){
        sliderLiveData.postValue(bannerRepository.getBanner())
    }
}