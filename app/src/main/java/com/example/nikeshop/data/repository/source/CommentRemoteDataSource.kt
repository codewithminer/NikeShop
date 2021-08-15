package com.example.nikeshop.data.repository.source

import com.example.nikeshop.data.Comment
import com.example.nikeshop.service.ApiService
import retrofit2.Response

class CommentRemoteDataSource(val apiService: ApiService): CommentDataSource {

    override suspend fun getAll(productId:Int): Response<List<Comment>> = apiService.getComment(productId)

    override suspend fun insert(): Comment {
        TODO("Not yet implemented")
    }
}