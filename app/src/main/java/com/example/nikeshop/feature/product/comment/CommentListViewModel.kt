package com.example.nikeshop.feature.product.comment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.data.Comment
import com.example.nikeshop.data.repository.CommentRepository
import kotlinx.coroutines.launch

class CommentListViewModel(val productId:Int,  val commentRepository: CommentRepository): NikeViewModel() {

    val commentsLiveData = MutableLiveData<List<Comment>>()

    init {
        callGetComment()
        progressBarLiveData.value = true
    }

     private fun callGetComment() = viewModelScope.launch {
        getComments()
    }

    private suspend fun getComments(){
        val result = commentRepository.getAll(productId)
        commentsLiveData.value = result.body()
        progressBarLiveData.postValue(false)
    }
}