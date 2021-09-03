package com.linh.web3jsample.domain.usecase

import com.linh.web3jsample.domain.entity.Token
import com.linh.web3jsample.domain.repository.SmartContractRepository
import com.linh.web3jsample.domain.repository.TokenRepository
import timber.log.Timber
import javax.inject.Inject

class GetAllOwnedTokensUseCase @Inject constructor(
    private val smartContractRepository: SmartContractRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(address: String): List<Token> {
        val ownedTokenIds = smartContractRepository.getOwnedTokenIdByAddress(address)
        return tokenRepository.getTokenMetadata(ownedTokenIds)
    }
}