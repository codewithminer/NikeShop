package com.example.nikeshop.feature.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.common.NikeFragment
import com.example.nikeshop.R
import com.example.nikeshop.common.EXTRA_KEY_DATA
import com.example.nikeshop.data.CartItem
import com.example.nikeshop.feature.product.ProductDetailActivity
import com.example.nikeshop.service.ImageLoadingService
import com.sevenlearn.nikestore.common.NikeCompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.item_cart.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class CartFragment: NikeFragment(), CartItemAdapter.CartItemViewCallBack {

    val cartViewModel: CartViewModel by viewModel()
    var cartItemAdapter: CartItemAdapter? = null
    val imageLoadingService: ImageLoadingService by inject()
    val compositeDisposable = CompositeDisposable()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel.progressBarLiveData.observe(viewLifecycleOwner){
            setProgressIndicator(it)
        }

        cartViewModel.cartItemLiveData.observe(viewLifecycleOwner) {
            Timber.i(it.toString())
            cartItemAdapter =
                CartItemAdapter(it as MutableList<CartItem>, imageLoadingService, this)
            cartItemsRv.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            cartItemsRv.adapter = cartItemAdapter
        }

        cartViewModel.purchaseDetailLiveData.observe(viewLifecycleOwner) {
            Timber.i(it.toString())
            cartItemAdapter?.let { adapter ->
                adapter.purchaseDetail = it
                adapter.notifyItemChanged(adapter.cartItems.size)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        cartViewModel.refresh()
    }

    override fun onRemoveCartItemClick(cartItem: CartItem) {
        cartViewModel.removeItemFromCart(cartItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeCompletableObserver(compositeDisposable){
                override fun onComplete() {
                    cartItemAdapter?.removeCartItem(cartItem)
                }
            })

    }

    override fun onIncreaseCartItemClick(cartItem: CartItem) {
            cartViewModel.increaseCartItemCount(cartItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : NikeCompletableObserver(compositeDisposable) {
                    override fun onComplete() {
                        cartItemAdapter?.increaseCount(cartItem)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        changeCountProgressBar.visibility = View.INVISIBLE
                        cartItemCountTv.visibility = View.VISIBLE
                    }
                })
    }

    override fun onDecreaseCartItemClick(cartItem: CartItem) {
            cartViewModel.decreaseCartItemCount(cartItem)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : NikeCompletableObserver(compositeDisposable) {
                    override fun onComplete() {
                        cartItemAdapter?.decreaseCount(cartItem)
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        changeCountProgressBar.visibility = View.INVISIBLE
                        cartItemCountTv.visibility = View.VISIBLE
                    }
                })
    }

    override fun onProductImageClick(cartItem: CartItem) {
        startActivity(Intent(requireContext(),ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_KEY_DATA, cartItem.product)
        })
    }
}