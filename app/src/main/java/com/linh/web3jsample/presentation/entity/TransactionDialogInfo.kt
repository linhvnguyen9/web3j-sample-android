package com.linh.web3jsample.presentation.entity

data class TransactionDialogInfo(val transaction: Transaction, val gasPrice: String)

enum class Transaction {
    APPROVE_FOR_TRADE
}