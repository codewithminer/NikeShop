package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Product
import retrofit2.Response

class ProductLocalDataSource: ProductDataSource {

    override suspend fun getProducts(sort:Int): Response<List<Product>> {
        TODO("Not yet implemented")
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