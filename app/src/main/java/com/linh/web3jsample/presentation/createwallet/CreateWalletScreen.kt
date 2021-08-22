package com.linh.web3jsample.presentation.createwallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CreateWalletScreen(
    viewModel: CreateWalletViewModel
) {
    Column(Modifier.fillMaxSize()) {
        Button({
            val password = "password"
            viewModel.createWallet(password)
        }) {
            Text("Create wallet")
        }
    }
}