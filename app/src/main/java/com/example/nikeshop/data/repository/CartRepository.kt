package com.example.nikeshop.data.repository

import com.example.nikeshop.data.*
import retrofit2.Response

interface CartRepository {

    suspend fun addToCart(productId:Int): Response<AddToCartResponse>
    suspend fun get(): CartResponse
    suspend fun remove(cartItemId:Int): Response<MessageResponse>
    suspend fun changeCount(cartItemId:Int, count:Int): Response<AddToCartResponse>
    suspend fun getCartItemsCount(): Response<CartItemCount>
}