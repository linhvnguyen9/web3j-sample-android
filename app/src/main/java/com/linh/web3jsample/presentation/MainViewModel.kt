package com.linh.web3jsample.presentation

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.domain.usecase.CreateWalletUseCase
import com.linh.web3jsample.domain.usecase.GetContractAddressUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val getContractAddressUseCase: GetContractAddressUseCase,
    private val createWalletUseCase: CreateWalletUseCase
) : ViewModel() {
    private val _contractAddress = MutableStateFlow("")
    val contractAddress: StateFlow<String>
        get() = _contractAddress

    private val _wallet = MutableStateFlow(Wallet("", "", ""))
    val wallet : StateFlow<Wallet>
        get() = _wallet

    init {
        getContractAddress()
    }

    private fun getContractAddress() {
        val contractAddress = getContractAddressUseCase()
        _contractAddress.value = contractAddress
    }

    fun createWallet(password: String) {
        viewModelScope.launch {
            _wallet.value = createWalletUseCase(password)
        }
    }
}