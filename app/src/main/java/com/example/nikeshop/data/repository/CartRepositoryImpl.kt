package com.example.nikeshop.data.repository

import com.example.nikeshop.data.AddToCartResponse
import com.example.nikeshop.data.CartItemCount
import com.example.nikeshop.data.CartResponse
import com.example.nikeshop.data.MessageResponse
import com.example.nikeshop.data.repository.source.CartDataSource
import io.reactivex.Single
import retrofit2.Response

class CartRepositoryImpl(val remoteDataSource: CartDataSource): CartRepository {

    override suspend fun addToCart(productId: Int): Response<AddToCartResponse> = remoteDataSource.addToCart(productId)

    override fun get(): Single<CartResponse> = remoteDataSource.get()

    override fun remove(cartItemId: Int): Single<MessageResponse>  = remoteDataSource.remove(cartItemId)

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse> = remoteDataSource.changeCount(cartItemId,count)

    override fun getCartItemsCount(): Single<CartItemCount>  = remoteDataSource.getCartItemsCount()
}