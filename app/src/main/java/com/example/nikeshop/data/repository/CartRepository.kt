package com.example.nikeshop.data.repository

import com.example.nikeshop.data.*
import io.reactivex.Single
import retrofit2.Response

interface CartRepository {

    suspend fun addToCart(productId:Int): Response<AddToCartResponse>
    fun get(): Single<CartResponse>
    fun remove(cartItemId:Int): Single<MessageResponse>
    fun changeCount(cartItemId:Int, count:Int): Single<AddToCartResponse>
    fun getCartItemsCount(): Single<CartItemCount>
}