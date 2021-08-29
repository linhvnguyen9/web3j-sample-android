package com.linh.web3jsample.data.di

import android.app.Application
import com.linh.web3jsample.data.contract.NonFungibleToken4
import com.linh.web3jsample.data.contract.SmartContractService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideWeb3j() : Web3j {
        return Web3j.build(HttpService("http://192.168.1.109:8545"))
    }

    @Singleton
    @Provides
    fun provideCredentials() : Credentials {
        return Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9")
    }

    @Singleton
    @Provides
    fun provideSmartContractService(web3j: Web3j, application: Application) : SmartContractService {
        return SmartContractService(web3j, application)
    }
}