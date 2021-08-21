package com.linh.web3jsample.data.contract

import com.linh.web3jsample.domain.entity.Wallet

data class LoadWalletResponse(
    val mnemonic: String,
    val privateKey: String,
    val address: String
) {
    fun toWallet() = Wallet(address = address, mnemonic = mnemonic, privateKey = privateKey)
}