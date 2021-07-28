package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Banner

interface BannerRepository {
    suspend fun getBanner(): List<Banner>
}