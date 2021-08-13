package com.example.nikeshop.feature.product

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.example.nikeshop.common.EXTRA_KEY_DATA
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.data.Product

class ProductDetailViewModel(bundle: Bundle):NikeViewModel() {

    val productLiveData = MutableLiveData<Product>()
    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
    }
}