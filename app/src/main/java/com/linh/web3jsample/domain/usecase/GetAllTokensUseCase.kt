package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.repository.SmartContractRepository
import com.linh.web3jsample.domain.repository.TokenRepository
import javax.inject.Inject

class GetAllTokensUseCase @Inject constructor(
    private val smartContractRepository: SmartContractRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(filter: String) = tokenRepository.getAllTokenMetadata(filter)
}