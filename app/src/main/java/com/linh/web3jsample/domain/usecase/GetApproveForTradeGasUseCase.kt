package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import javax.inject.Inject

class GetApproveForTradeGasUseCase @Inject constructor(private val repository: SmartContractRepository) {
    suspend operator fun invoke(tokenId: Long) = repository.estimateGasApproveForTrade(tokenId)
}