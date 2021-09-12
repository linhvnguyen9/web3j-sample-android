package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.entity.Trade
import com.linh.web3jsample.domain.repository.SmartContractRepository
import timber.log.Timber
import javax.inject.Inject

class GetTradeForTokenUseCase @Inject constructor(private val smartContractRepository: SmartContractRepository) {
    suspend operator fun invoke(tokenId: Long): Trade {
        val result = smartContractRepository.getTradeForToken(tokenId)
        Timber.d("GetTradeForTokenUseCase result $result")
        return result
    }
}