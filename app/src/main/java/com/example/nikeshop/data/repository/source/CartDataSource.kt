package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.AddToCartResponse
import com.example.nikeshop.data.CartItemCount
import com.example.nikeshop.data.CartResponse
import com.example.nikeshop.data.MessageResponse
import retrofit2.Response

interface CartDataSource {

    suspend fun addToCart(productId:Int): Response<AddToCartResponse>
    suspend fun get(): CartResponse
    suspend fun remove(cartItemId:Int): Response<MessageResponse>
    suspend fun changeCount(cartItemId:Int, count:Int): Response<AddToCartResponse>
    suspend fun getCartItemsCount(): Response<CartItemCount>
}