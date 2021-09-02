package com.linh.web3jsample.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.domain.usecase.GetEthBalanceUseCase
import com.linh.web3jsample.domain.usecase.GetWalletUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val getEthBalanceUseCase: GetEthBalanceUseCase,
    private val getWalletUseCase: GetWalletUseCase,
) : ViewModel() {
    private val _ethBalance = MutableStateFlow("")
    val ethBalance : StateFlow<String> get() = _ethBalance

    private val _wallet = MutableStateFlow(Wallet("", "", ""))
    val wallet : StateFlow<Wallet>
        get() = _wallet

    init {
        getWallet()
        getEthBalance()
    }

    private fun getWallet() {
        _wallet.value = getWalletUseCase()
    }

    private fun getEthBalance() {
        viewModelScope.launch {
            _ethBalance.value = getEthBalanceUseCase()
        }
    }
}