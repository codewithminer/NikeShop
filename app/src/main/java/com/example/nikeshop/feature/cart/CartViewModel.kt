package com.example.nikeshop.feature.cart

import androidx.lifecycle.MutableLiveData
import com.example.nikeshop.R
import com.example.nikeshop.common.NikeViewModel
import com.example.nikeshop.common.asyncNetworkRequest
import com.example.nikeshop.data.*
import com.example.nikeshop.data.repository.CartRepository
import com.sevenlearn.nikestore.common.NikeSingleObserver
import io.reactivex.Completable
import org.greenrobot.eventbus.EventBus
import timber.log.Timber

class CartViewModel(val cartRepository: CartRepository): NikeViewModel() {
    val cartItemLiveData = MutableLiveData<List<CartItem>>()
    val purchaseDetailLiveData = MutableLiveData<PurchaseDetail>()
    val emptyStateLiveData = MutableLiveData<EmptyState>()

    private fun getCartItem(){
        if (!TokenContainer.token.isNullOrEmpty()){
            emptyStateLiveData.value = EmptyState(false)
            progressBarLiveData.postValue(true)
            cartRepository.get()
                .asyncNetworkRequest()
                .doFinally { progressBarLiveData.value = false }
                .subscribe(object: NikeSingleObserver<CartResponse>(compositeDisposable){
                    override fun onSuccess(t: CartResponse) {
                        if(t.cart_items.isNotEmpty()){
                            cartItemLiveData.value = t.cart_items
                            purchaseDetailLiveData.value = PurchaseDetail(t.total_price, t.shipping_cost, t.payable_price)
                        }else{
                            emptyStateLiveData.value = EmptyState(true, R.string.cartEmptyState)
                        }

                    }
                })
        }else
            emptyStateLiveData.value = EmptyState(true, R.string.cartEmptyStateLoginRequired, true)
    }

    fun removeItemFromCart(cartItem: CartItem): Completable{
        return cartRepository.remove(cartItem.cart_item_id).doAfterSuccess {
            calculateAndPublishPurchaseDetail()
            val cartItemCount = EventBus.getDefault().getStickyEvent(CartItemCount::class.java)
            cartItemCount?.let {
                it.count -= cartItem.count
                EventBus.getDefault().postSticky(it)
            }
            Timber.i(cartItemLiveData.value.toString())
//            if(cartItemLiveData.value.isNullOrEmpty())
//                emptyStateLiveData.postValue(EmptyState(true, R.string.cartEmptyState))
            cartItemLiveData.value?.let {
                if(it.isEmpty())
                    emptyStateLiveData.postValue(EmptyState(true, R.string.cartEmptyState))
                }
        }.ignoreElement()
    }

    fun increaseCartItemCount(cartItem: CartItem): Completable {
        return cartRepository.changeCount(cartItem.cart_item_id, ++cartItem.count)
            .doAfterSuccess {
                calculateAndPublishPurchaseDetail()
                val cartItemCount = EventBus.getDefault().getStickyEvent(CartItemCount::class.java)
                cartItemCount?.let {
                    it.count += 1
                    EventBus.getDefault().postSticky(it)
                }
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
                val cartItemCount = EventBus.getDefault().getStickyEvent(CartItemCount::class.java)
                cartItemCount?.let {
                    it.count -= 1
                    EventBus.getDefault().postSticky(it)
                }
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