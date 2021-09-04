package com.linh.web3jsample.data.di

import com.linh.web3jsample.data.repository.SmartContractRepositoryImpl
import com.linh.web3jsample.data.repository.TokenRepositoryImpl
import com.linh.web3jsample.domain.repository.SmartContractRepository
import com.linh.web3jsample.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindSmartContractRepository(smartContractRepositoryImpl: SmartContractRepositoryImpl) : SmartContractRepository

    @Binds
    abstract fun bindTokenRepository(tokenRepositoryImpl: TokenRepositoryImpl) : TokenRepository
}