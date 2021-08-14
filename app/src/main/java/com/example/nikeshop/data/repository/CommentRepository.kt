package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Comment

interface CommentRepository {

    suspend fun getAll(productId:Int): List<Comment>

    suspend fun insert(): Comment
}