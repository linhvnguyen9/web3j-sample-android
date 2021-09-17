package com.linh.web3jsample.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.domain.usecase.CreateWalletUseCase
import com.linh.web3jsample.domain.usecase.GetAllTokensUseCase
import com.linh.web3jsample.domain.usecase.GetContractAddressUseCase
import com.linh.web3jsample.domain.usecase.GetWalletUseCase
import com.linh.web3jsample.presentation.NavigationDirections
import com.linh.web3jsample.presentation.NavigationManager
import com.linh.web3jsample.presentation.TokenDetailNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getContractAddressUseCase: GetContractAddressUseCase,
    private val getAllTokensUseCase: GetAllTokensUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _tokens = MutableStateFlow(emptyList<Token>())
    val tokens: StateFlow<List<Token>>
        get() = _tokens

    private val _query = MutableStateFlow("")
    val query: StateFlow<String>
        get() = _query

    private var job : Job? = null

    init {
        getAllTokens()
    }

    fun onClickToken(tokenId: Long) {
        navigationManager.navigate(TokenDetailNavigation.detail(tokenId))
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    fun getAllTokens() {
        getAllTokens(query.value)
    }

    private fun getAllTokens(filter: String) {
        job?.cancel()
        job = viewModelScope.launch {
            _tokens.value = getAllTokensUseCase(filter)
        }
    }
}