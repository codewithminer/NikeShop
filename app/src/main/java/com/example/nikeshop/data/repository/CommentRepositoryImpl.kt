package com.example.nikeshop.data.repository

import com.example.nikeshop.data.Comment
import com.example.nikeshop.data.repository.source.CommentDataSource

class CommentRepositoryImpl(val commentDataSource: CommentDataSource ): CommentRepository {

    override suspend fun getAll(productId:Int): List<Comment> = commentDataSource.getAll(productId)

    override suspend fun insert(): Comment {
        TODO("Not yet implemented")
    }
}