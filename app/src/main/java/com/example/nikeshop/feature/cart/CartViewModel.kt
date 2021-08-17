package com.example.nikeshop.feature.cart

import androidx.lifecycle.MutableLiveData
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.common.asyncNetworkRequest
import com.example.nikeshop.data.CartItem
import com.example.nikeshop.data.CartResponse
import com.example.nikeshop.data.PurchaseDetail
import com.example.nikeshop.data.TokenContainer
import com.example.nikeshop.data.repository.CartRepository
import com.sevenlearn.nikestore.common.NikeSingleObserver
import io.reactivex.Completable
import timber.log.Timber

class CartViewModel(val cartRepository: CartRepository): NikeViewModel() {
    val cartItemLiveData = MutableLiveData<List<CartItem>>()
    val purchaseDetailLiveData = MutableLiveData<PurchaseDetail>()

    private fun getCartItem(){
        if (!TokenContainer.token.isNullOrEmpty()){
            progressBarLiveData.postValue(true)
            cartRepository.get()
                .asyncNetworkRequest()
                .doFinally { progressBarLiveData.value = false }
                .subscribe(object: NikeSingleObserver<CartResponse>(compositeDisposable){
                    override fun onSuccess(t: CartResponse) {
                        if(t.cart_items.isNotEmpty()){
                            cartItemLiveData.value = t.cart_items
                            purchaseDetailLiveData.value = PurchaseDetail(t.total_price, t.shipping_cost, t.payable_price)
                        }

                    }
                })
        }
    }

    fun removeItemFromCart(cartItem: CartItem): Completable{
        return cartRepository.remove(cartItem.cart_item_id).doFinally {
            calculateAndPublishPurchaseDetail()
        }.ignoreElement()
    }

    fun increaseCartItemCount(cartItem: CartItem): Completable {
        return cartRepository.changeCount(cartItem.cart_item_id, ++cartItem.count)
            .doAfterSuccess {
                calculateAndPublishPurchaseDetail()
            }.doOnError {
                Timber.i(cartItem.toString())
                --cartItem.count
            }
            .ignoreElement()

    }

    fun decreaseCartItemCount(cartItem: CartItem): Completable =
        cartRepository.changeCount(cartItem.cart_item_id, --cartItem.count)
            .doAfterSuccess {
                calculateAndPublishPurchaseDetail()
            }
            .ignoreElement()

    fun refresh(){
        getCartItem()
    }

    private fun calculateAndPublishPurchaseDetail() {
        cartItemLiveData.value?.let { cartItems ->
            purchaseDetailLiveData.value?.let { purchaseDetail ->
                var totalPrice = 0
                var payablePrice = 0
                cartItems.forEach {
                    totalPrice += it.product.price * it.count
                    payablePrice += (it.product.price - it.product.discount) * it.count
                }

                purchaseDetail.totalPrice = totalPrice
                purchaseDetail.payable_price = payablePrice
                purchaseDetailLiveData.postValue(purchaseDetail)
            }
        }
    }
}