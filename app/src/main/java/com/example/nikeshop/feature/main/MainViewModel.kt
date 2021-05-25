package com.example.nikeshop.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.data.Product
import com.example.nikeshop.data.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel(val productRepository: ProductRepository): NikeViewModel() {

    val productsLiveData = MutableLiveData<List<Product>>()

    init {
    callGetProduct()
    }

    private fun callGetProduct() = viewModelScope.launch {
        getProduct()
    }

    private suspend fun getProduct(){
        productsLiveData.value = productRepository.getProducts()
    }
}