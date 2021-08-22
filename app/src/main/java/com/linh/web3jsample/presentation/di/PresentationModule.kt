package com.linh.web3jsample.presentation.di

import com.linh.web3jsample.presentation.NavigationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {
    @Singleton
    @Provides
    fun providesNavigationManager() = NavigationManager()
}