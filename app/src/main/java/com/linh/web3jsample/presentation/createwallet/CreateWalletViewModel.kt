package com.linh.web3jsample.presentation.createwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.domain.usecase.CreateWalletUseCase
import com.linh.web3jsample.presentation.NavigationDirections
import com.linh.web3jsample.presentation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateWalletViewModel @Inject constructor(
    private val createWalletUseCase: CreateWalletUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _wallet = MutableStateFlow(Wallet("", "", ""))
    val wallet : StateFlow<Wallet>
        get() = _wallet

    fun createWallet(password: String) {
        viewModelScope.launch {
            _wallet.value = createWalletUseCase(password)
            navigationManager.navigate(NavigationDirections.main)
        }
    }

    fun onClickContinue() {
        navigationManager.navigate(NavigationDirections.main)
    }
}