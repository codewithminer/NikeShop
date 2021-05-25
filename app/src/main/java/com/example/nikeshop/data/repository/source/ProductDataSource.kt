package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Product

interface ProductDataSource {

    suspend fun getProducts(): List<Product>

    suspend fun getFavoriteProduct(): List<Product>

    suspend fun removeFromFavorite()

    fun addToFavorite()


}