package com.example.nikeshop

import android.app.Application
import android.os.Bundle
import com.example.nikeshop.data.repository.*
import com.example.nikeshop.data.repository.source.*
import com.example.nikeshop.feature.list.ProductListViewModel
import com.example.nikeshop.feature.main.MainViewModel
import com.example.nikeshop.feature.main.ProductListAdapter
import com.example.nikeshop.feature.product.ProductDetailViewModel
import com.example.nikeshop.feature.product.comment.CommentListViewModel
import com.example.nikeshop.service.FrescoImageLoadingService
import com.example.nikeshop.service.ImageLoadingService
import com.example.nikeshop.service.createApiServiceInstance
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Fresco.initialize(this)

        val myModules = module {
            single { createApiServiceInstance() }
            single<ImageLoadingService> { FrescoImageLoadingService() }

            factory<ProductRepository> { ProductRepositoryImpl(ProductRemoteDataSource(get()),
                ProductLocalDataSource()
            ) }
            factory { (viewType:Int) -> ProductListAdapter(viewType ,get()) }
            factory<BannerRepository> { BannerRepositoryImpl(BannerRemoteDataSource(get())) }
            factory<CommentRepository> { CommentRepositoryImpl(CommentRemoteDataSource(get())) }
            factory<CartRepository> { CartRepositoryImpl(CartRemoteDataSource(get())) }

            viewModel { MainViewModel(get(),get()) }
            viewModel { (bundle: Bundle) -> ProductDetailViewModel(bundle,get(), get()) }
            viewModel { (productId: Int) -> CommentListViewModel(productId,get()) }
            viewModel {(sort: Int) -> ProductListViewModel(sort, get())}

        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}