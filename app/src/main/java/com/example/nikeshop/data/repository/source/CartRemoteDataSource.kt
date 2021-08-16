package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.AddToCartResponse
import com.example.nikeshop.data.CartItemCount
import com.example.nikeshop.data.CartResponse
import com.example.nikeshop.data.MessageResponse
import com.example.nikeshop.service.ApiService
import com.google.gson.JsonObject
import retrofit2.Response

class CartRemoteDataSource(val apiService: ApiService): CartDataSource {

    override suspend fun addToCart(productId: Int): Response<AddToCartResponse> = apiService.addToCart(
        JsonObject().apply {
            addProperty("product_id", productId)
        }
    )

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