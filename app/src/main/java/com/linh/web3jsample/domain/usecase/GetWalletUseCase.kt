package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import javax.inject.Inject

class GetWalletUseCase @Inject constructor(
    private val repository: SmartContractRepository
) {
    operator fun invoke() = repository.getWallet()
}