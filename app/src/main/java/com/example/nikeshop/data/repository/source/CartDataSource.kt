package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.AddToCartResponse
import com.example.nikeshop.data.CartItemCount
import com.example.nikeshop.data.CartResponse
import com.example.nikeshop.data.MessageResponse
import io.reactivex.Single
import retrofit2.Response

interface CartDataSource {

    suspend fun addToCart(productId:Int): Response<AddToCartResponse>
    fun get(): Single<CartResponse>
    fun remove(cartItemId:Int): Single<MessageResponse>
    fun changeCount(cartItemId:Int, count:Int): Single<AddToCartResponse>
    fun getCartItemsCount(): Single<CartItemCount>
}