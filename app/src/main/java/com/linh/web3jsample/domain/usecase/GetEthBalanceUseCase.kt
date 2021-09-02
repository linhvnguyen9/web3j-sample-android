package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import javax.inject.Inject

class GetEthBalanceUseCase @Inject constructor(private val smartContractRepository: SmartContractRepository) {
    suspend operator fun invoke() = smartContractRepository.getEthBalance()
}