package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Banner
import com.example.nikeshop.service.ApiService

class BannerRemoteDataSource(val apiService: ApiService): BannerDataSource {
    override suspend fun getBanner(): List<Banner> = apiService.getBanner()
}