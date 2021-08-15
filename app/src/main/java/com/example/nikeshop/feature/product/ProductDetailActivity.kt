package com.example.nikeshop.feature.product

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.common.EXTRA_KEY_ID
import com.example.nikeshop.common.NikeActivity
import com.example.nikeshop.common.formatPrice
import com.example.nikeshop.data.Comment
import com.example.nikeshop.feature.product.comment.CommentAdapter
import com.example.nikeshop.feature.product.comment.CommentListActivity
import com.example.nikeshop.service.ImageLoadingService
import com.example.nikeshop.view.scroll.ObservableScrollView
import com.example.nikeshop.view.scroll.ObservableScrollViewCallbacks
import com.example.nikeshop.view.scroll.ScrollState
import kotlinx.android.synthetic.main.activity_product_detail.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class ProductDetailActivity : NikeActivity() {

    val productDetailViewModel: ProductDetailViewModel by viewModel { parametersOf(intent.extras) }
    val imageLoadingService:ImageLoadingService by inject()
    val commentAdapter = CommentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)


        productDetailViewModel.progressBarLiveData.observe(this){
            setProgressIndicator(it)
        }

        productDetailViewModel.productLiveData.observe(this){
            imageLoadingService.load(productIvDetail, it.image)
            productTitleDetail.text = it.title
            previousPriceDetail.text = formatPrice(it.previous_price)
            previousPriceDetail.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            currentPriceDetail.text = formatPrice(it.price)
            toolbarTitleTv.text = it.title

        }

        productDetailViewModel.commentLiveData.observe(this){
            Timber.i(it.toString())
            commentAdapter.comments = it as ArrayList<Comment>
            if(it.size > 3){
                viewAllBtn.visibility = View.VISIBLE
                viewAllBtn.setOnClickListener {
                    startActivity(Intent(this, CommentListActivity::class.java).apply {
                        putExtra(EXTRA_KEY_ID, productDetailViewModel.productLiveData.value!!.id)
                    })
                }
            }

        }

        initViews()
    }

    fun initViews(){

        commentRv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        commentRv.adapter = commentAdapter

        productIvDetail.post {
            val productImageHeight = productIvDetail.height
            val toolbarLayout = toolbarView
            val productImageDetail = productIvDetail

            observableScrollView.addScrollViewCallbacks(object: ObservableScrollViewCallbacks{
                override fun onScrollChanged(
                    scrollY: Int,
                    firstScroll: Boolean,
                    dragging: Boolean
                ) {
                    toolbarLayout.alpha = scrollY.toFloat() / productImageHeight.toFloat()
                    productImageDetail.translationY = scrollY.toFloat() / 2
                }

                override fun onDownMotionEvent() {
                }

                override fun onUpOrCancelMotionEvent(scrollState: ScrollState?) {
                }

            })
        }
    }
}