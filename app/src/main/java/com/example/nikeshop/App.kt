package com.example.nikeshop

import android.app.Application
import com.example.nikeshop.data.repository.ProductRepository
import com.example.nikeshop.data.repository.ProductRepositoryImpl
import com.example.nikeshop.data.repository.source.ProductLocalDataSource
import com.example.nikeshop.data.repository.source.ProductRemoteDataSource
import com.example.nikeshop.feature.main.MainViewModel
import com.example.nikeshop.service.createApiServiceInstance
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant()

        val myModules = module {
            single { createApiServiceInstance() }
            factory<ProductRepository> { ProductRepositoryImpl(ProductRemoteDataSource(get()),
                ProductLocalDataSource()
            ) }
            viewModel { MainViewModel(get()) }
        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }
    }
}