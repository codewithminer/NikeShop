package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Comment

interface CommentDataSource {

    suspend fun getAll(productId:Int): List<Comment>

    suspend fun insert(): Comment
}