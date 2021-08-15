package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Banner
import retrofit2.Response

interface BannerRepository {
    suspend fun getBanner(): Response<List<Banner>>
}