package com.example.nikeshop.feature.product

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nikeshop.common.EXTRA_KEY_DATA
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.data.AddToCartResponse
import com.example.nikeshop.data.Comment
import com.example.nikeshop.data.Product
import com.example.nikeshop.data.repository.CartRepository
import com.example.nikeshop.data.repository.CommentRepository
import kotlinx.coroutines.launch
import retrofit2.Response
class ProductDetailViewModel(
    bundle: Bundle,
    val commentRepository: CommentRepository,
    val cartRepository:CartRepository)
    :NikeViewModel() {

    val productLiveData = MutableLiveData<Product>()
    val commentLiveData = MutableLiveData<List<Comment>>()

    init {
        progressBarLiveData.value = true
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        callGetComment()
    }

    private fun callGetComment() = viewModelScope.launch {
        getComment()
    }

    private suspend fun getComment(){
        val result = commentRepository.getAll(productLiveData.value!!.id)
        commentLiveData.value = result.body()
        progressBarLiveData.postValue(false)

    }

      fun CallAddToCart() = viewModelScope.launch{
         onAddToCart()
    }

    suspend fun onAddToCart(): Response<AddToCartResponse>{
       return cartRepository.addToCart(productLiveData.value!!.id)
    }
}

