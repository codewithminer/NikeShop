package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Banner

interface BannerDataSource {
    suspend fun getBanner(): List<Banner>
}