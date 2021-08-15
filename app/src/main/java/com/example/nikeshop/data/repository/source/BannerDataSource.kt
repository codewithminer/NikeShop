package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Banner
import retrofit2.Response

interface BannerDataSource {
    suspend fun getBanner(): Response<List<Banner>>
}