package com.linh.web3jsample.presentation.tokendetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.entity.Trade
import com.linh.web3jsample.domain.usecase.*
import com.linh.web3jsample.presentation.NavigationManager
import com.linh.web3jsample.presentation.entity.Transaction
import com.linh.web3jsample.presentation.entity.TransactionDialogInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenDetailViewModel @Inject constructor(
    private val getTokenDetailUseCase: GetTokenDetailUseCase,
    private val approveForTradeUseCase: ApproveForTradeUseCase,
    private val getTradeForTokenUseCase: GetTradeForTokenUseCase,
    private val getApproveForTradeGasUseCase: GetApproveForTradeGasUseCase,
    private val getOpenTradeGasUseCase: GetOpenTradeGasUseCase,
    private val getExecuteTradeGasUseCase: GetExecuteTradeGasUseCase,
    private val executeTradeUseCase: ExecuteTradeUseCase,
    private val openTradeUseCase: OpenTradeUseCase
) : ViewModel() {
    private val _tokenDetail = MutableStateFlow(Token(0L, "", "", "", ""))
    val tokenDetail: StateFlow<Token> get() = _tokenDetail

    private val _trade = MutableStateFlow(Trade(0, "", 0L, "", ""))
    val trade: StateFlow<Trade> get() = _trade

    private val _transaction = MutableStateFlow<TransactionDialogInfo?>(null)
    val transaction: StateFlow<TransactionDialogInfo?> get() = _transaction

    val tradePrice = MutableStateFlow("")

    fun getTokenDetail(tokenId: Long) {
        viewModelScope.launch {
            _tokenDetail.value = getTokenDetailUseCase(tokenId)
            _trade.value = getTradeForTokenUseCase(tokenId)
        }
    }

    fun approveForTrade() {
        viewModelScope.launch {
            val gas = getApproveForTradeGasUseCase(tokenDetail.value.id)
            _transaction.value = TransactionDialogInfo(Transaction.APPROVE_FOR_TRADE, gas)
        }
    }

    fun confirmApproveForTrade() {
        viewModelScope.launch {
            resetTransaction()
            approveForTradeUseCase(tokenDetail.value.id)
        }
    }

    fun onClickCreateTrade() {
        viewModelScope.launch {
            val gas = getOpenTradeGasUseCase(tokenDetail.value.id, tradePrice.value)
            _transaction.value = TransactionDialogInfo(Transaction.CREATE_TRADE, gas)
        }
    }

    fun confirmCreateTrade() {
        viewModelScope.launch {
            resetTransaction()
            openTradeUseCase(tokenDetail.value.id, tradePrice.value)
        }
    }

    fun onClickBuy() {
        viewModelScope.launch {
            val gas = getExecuteTradeGasUseCase(_trade.value.id)
            _transaction.value = TransactionDialogInfo(Transaction.EXECUTE_TRADE, gas, _trade.value.price)
        }
    }

    fun confirmExecuteTrade() {
        viewModelScope.launch {
            resetTransaction()
            executeTradeUseCase(_trade.value.id)
        }
    }

    fun resetTransaction() {
        _transaction.value = null
    }
}