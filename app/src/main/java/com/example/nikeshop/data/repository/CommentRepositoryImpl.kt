package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Comment
import com.example.nikeshop.data.repository.source.CommentDataSource
import retrofit2.Response

class CommentRepositoryImpl(val commentDataSource: CommentDataSource ): CommentRepository {

    override suspend fun getAll(productId:Int): Response<List<Comment>> = commentDataSource.getAll(productId)

    override suspend fun insert(): Comment {
        TODO("Not yet implemented")
    }
}