package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Banner
import com.example.nikeshop.data.repository.source.BannerRemoteDataSource

class BannerRepositoryImpl(val bannerRemoteDataSource: BannerRemoteDataSource): BannerRepository {
    override suspend fun getBanner(): List<Banner> = bannerRemoteDataSource.getBanner()
}