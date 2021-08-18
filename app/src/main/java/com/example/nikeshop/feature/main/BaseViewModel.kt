package com.example.nikeshop.feature.main

import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.data.CartItemCount
import com.example.nikeshop.data.TokenContainer
import com.example.nikeshop.data.repository.CartRepository
import com.sevenlearn.nikestore.common.NikeSingleObserver
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class BaseViewModel(val cartRepository: CartRepository): NikeViewModel() {

    fun getCartItemCount(){
        if (!TokenContainer.token.isNullOrEmpty()){
            cartRepository.getCartItemsCount()
                .subscribeOn(Schedulers.io())
                .subscribe(object: NikeSingleObserver<CartItemCount>(compositeDisposable){
                    override fun onSuccess(t: CartItemCount) {
                        EventBus.getDefault().postSticky(t)
                    }
                })
        }
    }
}