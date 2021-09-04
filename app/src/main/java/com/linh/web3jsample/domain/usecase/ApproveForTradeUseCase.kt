package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import com.linh.web3jsample.domain.repository.TokenRepository
import javax.inject.Inject

class ApproveForTradeUseCase @Inject constructor(
    private val smartContractRepository: SmartContractRepository
) {
    suspend operator fun invoke(tokenId: Long) = smartContractRepository.approveForTrade(tokenId)
}