package com.example.nikeshop.service

import com.example.nikeshop.view.NikeImageView

interface ImageLoadingService {

    fun load(imageView:NikeImageView, imageUrl: String)
}