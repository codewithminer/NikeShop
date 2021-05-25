package com.example.nikeshop

import android.app.Application
import com.example.nikeshop.service.createApiServiceInstance
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App: Application(){

    override fun onCreate() {
        super.onCreate()

        val modules = module {
            single { createApiServiceInstance() }
        }

        startKoin { modules}
    }
}