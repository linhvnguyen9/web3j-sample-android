package com.linh.web3jsample.presentation.tokendetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.usecase.GetTokenDetailUseCase
import com.linh.web3jsample.presentation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenDetailViewModel @Inject constructor(
    private val getTokenDetailUseCase: GetTokenDetailUseCase,
) : ViewModel() {
    private val _tokenDetail = MutableStateFlow(Token(0L, "", "", "", ""))
    val tokenDetail: StateFlow<Token> get() = _tokenDetail

    fun getTokenDetail(tokenId: Long) {
        viewModelScope.launch {
            _tokenDetail.value = getTokenDetailUseCase(tokenId)
        }
    }
}