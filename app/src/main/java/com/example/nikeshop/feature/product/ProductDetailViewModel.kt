package com.example.nikeshop.feature.product

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nikeshop.common.EXTRA_KEY_DATA
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.data.Comment
import com.example.nikeshop.data.Product
import com.example.nikeshop.data.repository.CommentRepository
import kotlinx.coroutines.launch

class ProductDetailViewModel(bundle: Bundle,val commentRepository: CommentRepository):NikeViewModel() {

    val productLiveData = MutableLiveData<Product>()
    val commentLiveData = MutableLiveData<List<Comment>>()
    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        callGetComment()
    }

    private fun callGetComment() = viewModelScope.launch {
        getComment()
    }

    private suspend fun getComment(){
        commentLiveData.value = commentRepository.getAll(productLiveData.value!!.id)

    }
}