package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import javax.inject.Inject

class CancelTradeUseCase  @Inject constructor(private val repository: SmartContractRepository) {
    suspend operator fun invoke(tradeId: Long) = repository.cancelTrade(tradeId)
}