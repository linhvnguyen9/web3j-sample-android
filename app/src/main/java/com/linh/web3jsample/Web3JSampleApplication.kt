package com.linh.web3jsample

import android.app.Application
import com.linh.web3jsample.data.di.dataModule
import com.linh.web3jsample.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Web3JSampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Web3JSampleApplication)
            val modules = modules(
                listOf(
                    dataModule,
                    presentationModule
                )
            )
        }
    }
}