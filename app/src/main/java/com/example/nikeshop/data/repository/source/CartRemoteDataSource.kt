package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.AddToCartResponse
import com.example.nikeshop.data.CartItemCount
import com.example.nikeshop.data.CartResponse
import com.example.nikeshop.data.MessageResponse
import com.example.nikeshop.service.ApiService
import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Response

class CartRemoteDataSource(val apiService: ApiService): CartDataSource {

    override suspend fun addToCart(productId: Int): Response<AddToCartResponse> = apiService.addToCart(
        JsonObject().apply {
            addProperty("product_id", productId)
        }
    )

    override fun get(): Single<CartResponse> = apiService.getCart()

    override fun remove(cartItemId: Int): Single<MessageResponse>  = apiService.removeItemFromCart(
        JsonObject().apply {
            addProperty("cart_item_id", cartItemId)
        }
    )

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse>  = apiService.changeCount(JsonObject().apply {
        addProperty("cart_item_id",cartItemId)
        addProperty("count",count)
    })

    override fun getCartItemsCount(): Single<CartItemCount> = apiService.getCartItemCount()
}