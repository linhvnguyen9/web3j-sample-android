package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import javax.inject.Inject

class GetExecuteTradeGasUseCase @Inject constructor(private val repository: SmartContractRepository) {
    suspend operator fun invoke(tradeId: Long, value: String) = repository.estimateGasExecuteTrade(tradeId, value)
}