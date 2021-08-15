package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Product
import com.example.nikeshop.data.repository.source.ProductDataSource
import com.example.nikeshop.data.repository.source.ProductLocalDataSource
import retrofit2.Response

class ProductRepositoryImpl(
    val remoteDataSource: ProductDataSource,
    val localDataSource: ProductLocalDataSource
): ProductRepository {

    override suspend fun getProducts(sort:Int): Response<List<Product>> {
        return remoteDataSource.getProducts(sort)
    }

    override suspend fun getFavoriteProduct(): List<Product> {
        TODO("Not yet implemented")
    }

    override suspend fun removeFromFavorite() {
        TODO("Not yet implemented")
    }

    override fun addToFavorite() {
        TODO("Not yet implemented")
    }
}