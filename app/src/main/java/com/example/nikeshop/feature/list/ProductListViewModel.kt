package com.example.nikeshop.feature.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nikeshop.R
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.data.Product
import com.example.nikeshop.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductListViewModel(var sort:Int, val productRepository: ProductRepository): NikeViewModel() {

    val productsLiveData = MutableLiveData<List<Product>>()
    val selectedSortTitleLiveData = MutableLiveData<Int>()
    val sortTitles = arrayOf(R.string.sortLatest, R.string.sortPopular, R.string.sortPriceHTL, R.string.sortPriceLTH)
    init {
        callGetProduct()
        progressBarLiveData.value = true
        selectedSortTitleLiveData.value = sortTitles[sort]
    }

    private fun callGetProduct() = viewModelScope.launch {
        getProduct()
    }

    suspend fun getProduct(){
        productsLiveData.value = productRepository.getProducts(sort)
        progressBarLiveData.value = false
    }

    fun onSelectedSortChangedByUser(sort:Int){
        this.sort = sort
        this.selectedSortTitleLiveData.value = sortTitles[sort]
        callGetProduct()

    }

}