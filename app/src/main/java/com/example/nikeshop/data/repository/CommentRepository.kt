package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Comment
import retrofit2.Response

interface CommentRepository {

    suspend fun getAll(productId:Int): Response<List<Comment>>

    suspend fun insert(): Comment
}