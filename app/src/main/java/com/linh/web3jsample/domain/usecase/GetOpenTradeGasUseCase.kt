package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import javax.inject.Inject

class GetOpenTradeGasUseCase @Inject constructor(private val repository: SmartContractRepository) {
    suspend operator fun invoke(itemId: Long, priceInEth: String) = repository.estimateGasOpenTrade(itemId, priceInEth)
}