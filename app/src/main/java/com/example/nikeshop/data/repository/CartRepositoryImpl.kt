package com.example.nikeshop.data.repository

import com.example.nikeshop.data.AddToCartResponse
import com.example.nikeshop.data.CartItemCount
import com.example.nikeshop.data.CartResponse
import com.example.nikeshop.data.MessageResponse
import com.example.nikeshop.data.repository.source.CartDataSource
import retrofit2.Response

class CartRepositoryImpl(val remoteDataSource: CartDataSource): CartRepository {

    override suspend fun addToCart(productId: Int): Response<AddToCartResponse> = remoteDataSource.addToCart(productId)

    override suspend fun get(): CartResponse {
        TODO("Not yet implemented")
    }

    override suspend fun remove(cartItemId: Int): Response<MessageResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun changeCount(cartItemId: Int, count: Int): Response<AddToCartResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getCartItemsCount(): Response<CartItemCount> {
        TODO("Not yet implemented")
    }
}