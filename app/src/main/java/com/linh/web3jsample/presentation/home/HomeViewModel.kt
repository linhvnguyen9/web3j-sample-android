package com.linh.web3jsample.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.domain.usecase.CreateWalletUseCase
import com.linh.web3jsample.domain.usecase.GetContractAddressUseCase
import com.linh.web3jsample.domain.usecase.GetWalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getContractAddressUseCase: GetContractAddressUseCase,
    private val getWalletUseCase: GetWalletUseCase
) : ViewModel() {
    private val _contractAddress = MutableStateFlow("")
    val contractAddress: StateFlow<String>
        get() = _contractAddress

    private val _wallet = MutableStateFlow(Wallet("", "", ""))
    val wallet : StateFlow<Wallet>
        get() = _wallet

    init {
        getWallet()
        getContractAddress()
    }

    private fun getContractAddress() {
        val contractAddress = getContractAddressUseCase()
        _contractAddress.value = contractAddress
    }

    private fun getWallet() {
        _wallet.value = getWalletUseCase()
    }
}