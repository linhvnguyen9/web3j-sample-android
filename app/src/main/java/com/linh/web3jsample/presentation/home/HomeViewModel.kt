package com.linh.web3jsample.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getContractAddressUseCase: GetContractAddressUseCase,
    private val getAllTokensUseCase: GetAllTokensUseCase,
    private val navigationManager: NavigationManager
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String>
        get() = _query

    @FlowPreview
    @ExperimentalCoroutinesApi
    val tokens: Flow<PagingData<Token>> = query.flatMapLatest { getAllTokens(query.value).debounce(500) }.distinctUntilChanged()

    fun onClickToken(tokenId: Long) {
        navigationManager.navigate(TokenDetailNavigation.detail(tokenId))
    }

    fun setQuery(query: String) {
        Timber.d("setQuery query $query")
        _query.value = query
    }

    private fun getAllTokens(filter: String): Flow<PagingData<Token>> {
        return getAllTokensUseCase(filter)
    }
}