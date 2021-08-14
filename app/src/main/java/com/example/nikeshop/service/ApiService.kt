package com.example.nikeshop.service

import com.example.nikeshop.data.Banner
import com.example.nikeshop.data.Comment
import com.example.nikeshop.data.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("product/list")
    suspend fun getProduct(@Query("sort") sort:String): List<Product>

    @GET("banner/slider")
    suspend fun getBanner(): List<Banner>

    @GET("comment/list")
    suspend fun getComment(@Query("product_id") productId:Int): List<Comment>
}

fun createApiServiceInstance(): ApiService{
    val retrofit = Retrofit.Builder()
        .baseUrl("http://expertdevelopers.ir/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(ApiService::class.java)
}