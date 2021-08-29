package com.linh.web3jsample

import android.app.Application
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.HiltAndroidApp
import org.bouncycastle.jce.provider.BouncyCastleProvider
import timber.log.Timber
import java.security.Provider
import java.security.Security

@HiltAndroidApp
class Web3JSampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setupDependencies()
    }

    private fun setupDependencies() {
        setupTimber()
        setupHawk()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun setupHawk() {
        Hawk.init(this).build()
    }
}