package com.linh.web3jsample.presentation.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.domain.usecase.GetAllOwnedTokensUseCase
import com.linh.web3jsample.domain.usecase.GetAllTokensUseCase
import com.linh.web3jsample.domain.usecase.GetEthBalanceUseCase
import com.linh.web3jsample.domain.usecase.GetWalletUseCase
import com.linh.web3jsample.presentation.NavigationDirections
import com.linh.web3jsample.presentation.NavigationManager
import com.linh.web3jsample.presentation.TokenDetailNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val getEthBalanceUseCase: GetEthBalanceUseCase,
    private val getWalletUseCase: GetWalletUseCase,
    private val getAllOwnedTokensUseCase: GetAllOwnedTokensUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _ethBalance = MutableStateFlow("")
    val ethBalance : StateFlow<String> get() = _ethBalance

    private val _wallet = MutableStateFlow(Wallet("", "", ""))
    val wallet : StateFlow<Wallet>
        get() = _wallet

    private val _ownedTokens = MutableStateFlow(emptyList<Token>())
    val ownedTokens : StateFlow<List<Token>>
        get() = _ownedTokens

    init {
        getWallet()
        getEthBalance()
        getAllOwnedTokens()
    }

    fun onClickOwnedToken(id: Long) {
        navigationManager.navigate(TokenDetailNavigation.detail(id))
    }

    private fun getWallet() {
        _wallet.value = getWalletUseCase()
    }

    private fun getEthBalance() {
        viewModelScope.launch {
            _ethBalance.value = getEthBalanceUseCase()
        }
    }

    private fun getAllOwnedTokens() {
        viewModelScope.launch {
            _ownedTokens.value = getAllOwnedTokensUseCase(_wallet.value.address)
        }
    }
}