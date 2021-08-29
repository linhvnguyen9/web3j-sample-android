package com.linh.web3jsample.data.local

import com.orhanobut.hawk.Hawk

object EncryptedSharedPreference {
    fun saveWalletAddress(address: String) {
        Hawk.put(KEY_WALLET_ADDRESS, address)
    }

    fun getWalletAddress(): String {
        return Hawk.get<String>(KEY_WALLET_ADDRESS)
    }

    fun saveWalletPrivateKey(privateKey: String) {
        Hawk.put(KEY_WALLET_PRIVATE_KEY, privateKey)
    }

    fun getWalletPrivateKey(): String {
        return Hawk.get<String>(KEY_WALLET_PRIVATE_KEY)
    }

    fun saveWalletPassword(password: String) {
        Hawk.put(KEY_WALLET_PASSWORD, password)
    }

    fun getWalletPassword(): String {
        return Hawk.get(KEY_WALLET_PASSWORD)
    }

    fun saveWalletMnemonic(mnemonic: String) {
        Hawk.put(KEY_WALLET_MNEMONIC, mnemonic)
    }

    fun getWalletMnemonic(): String {
        return Hawk.get(KEY_WALLET_MNEMONIC)
    }

    private const val KEY_WALLET_ADDRESS = "KEY_WALLET_ADDRESS"
    private const val KEY_WALLET_PRIVATE_KEY = "KEY_WALLET_PRIVATE_KEY"
    private const val KEY_WALLET_PASSWORD = "KEY_WALLET_PASSWORD"
    private const val KEY_WALLET_MNEMONIC = "KEY_WALLET_MNEMONIC"
}