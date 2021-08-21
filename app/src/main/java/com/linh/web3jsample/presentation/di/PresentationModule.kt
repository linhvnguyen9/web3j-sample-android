package com.linh.web3jsample.presentation.di

import com.linh.web3jsample.domain.usecase.CreateWalletUseCase
import com.linh.web3jsample.domain.usecase.GetContractAddressUseCase
import com.linh.web3jsample.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { GetContractAddressUseCase(get()) }
    factory { CreateWalletUseCase(get()) }

    viewModel { MainViewModel(get(), get()) }
}