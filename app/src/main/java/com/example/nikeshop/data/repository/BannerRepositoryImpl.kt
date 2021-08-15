package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Banner
import com.example.nikeshop.data.repository.source.BannerRemoteDataSource
import retrofit2.Response

class BannerRepositoryImpl(val bannerRemoteDataSource: BannerRemoteDataSource): BannerRepository {
    override suspend fun getBanner(): Response<List<Banner>> = bannerRemoteDataSource.getBanner()
}