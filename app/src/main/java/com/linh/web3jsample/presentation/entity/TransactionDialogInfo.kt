package com.linh.web3jsample.presentation.entity

data class TransactionDialogInfo(val transaction: Transaction, val gasPrice: String, val value: String? = null)

enum class Transaction {
    APPROVE_FOR_TRADE,
    CREATE_TRADE,
    EXECUTE_TRADE
}