package com.linh.web3jsample.data.di

import android.app.Application
import com.linh.web3jsample.data.contract.TokenContractService
import com.linh.web3jsample.data.contract.TokenMetadataService
import com.linh.web3jsample.data.contract.TradeContractService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideCredentials(): Credentials {
        return Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9")
    }

    @Singleton
    @Provides
    fun provideTokenContractService(web3j: Web3j, application: Application): TokenContractService {
        return TokenContractService(web3j, application)
    }

    @Singleton
    @Provides
    fun provideTradeContractService(web3j: Web3j, application: Application): TradeContractService {
        return TradeContractService(web3j, application)
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkhttp(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideTokenMetadataService(retrofit: Retrofit): TokenMetadataService {
        return retrofit.create(TokenMetadataService::class.java)
    }
}