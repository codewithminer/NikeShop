package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Product
import retrofit2.Response

interface ProductRepository {

    suspend fun getProducts(sort:Int): Response<List<Product>>

    suspend fun getFavoriteProduct(): List<Product>

    suspend fun removeFromFavorite()

    fun addToFavorite()

}