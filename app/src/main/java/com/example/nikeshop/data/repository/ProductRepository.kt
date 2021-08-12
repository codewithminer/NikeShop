package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Product

interface ProductRepository {

    suspend fun getProducts(sort:Int): List<Product>

    suspend fun getFavoriteProduct(): List<Product>

    suspend fun removeFromFavorite()

    fun addToFavorite()

}