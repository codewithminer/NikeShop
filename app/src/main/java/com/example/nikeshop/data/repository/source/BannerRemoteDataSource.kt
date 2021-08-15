package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Banner
import com.example.nikeshop.service.ApiService
import retrofit2.Response

class BannerRemoteDataSource(val apiService: ApiService): BannerDataSource {
    override suspend fun getBanner(): Response<List<Banner>> = apiService.getBanner()
}