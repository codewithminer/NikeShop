package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Product
import com.example.nikeshop.service.ApiService
import retrofit2.Response

class ProductRemoteDataSource(val apiService: ApiService): ProductDataSource {

    override suspend fun getProducts(sort:Int): Response<List<Product>> {
        return apiService.getProduct(sort.toString())
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