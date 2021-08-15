package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Comment
import retrofit2.Response

interface CommentDataSource {

    suspend fun getAll(productId:Int): Response<List<Comment>>

    suspend fun insert(): Comment
}