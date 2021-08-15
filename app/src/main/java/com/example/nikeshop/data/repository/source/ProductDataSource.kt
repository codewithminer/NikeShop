package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Product
import retrofit2.Response

interface ProductDataSource {

    suspend fun getProducts(sort:Int): Response<List<Product>>

    suspend fun getFavoriteProduct(): List<Product>

    suspend fun removeFromFavorite()

    fun addToFavorite()


}