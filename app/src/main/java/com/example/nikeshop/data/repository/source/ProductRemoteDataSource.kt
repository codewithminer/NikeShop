package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Product
import com.example.nikeshop.service.ApiService

class ProductRemoteDataSource(val apiService: ApiService): ProductDataSource {

    override suspend fun getProducts(): List<Product> {
        return apiService.getProduct()
    }

    override suspend fun getFavoriteProduct(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorite() {
        TODO("Not yet implemented")
    }

    override fun addToFavorite() {
        TODO("Not yet implemented")
    }
}