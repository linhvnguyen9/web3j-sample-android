package com.linh.web3jsample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.presentation.theme.Web3JSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Web3JSampleTheme {
                // A surface container using the 'background' color from the theme
                val contractAddress = viewModel.contractAddress.collectAsState("")
                val wallet = viewModel.wallet.collectAsState(Wallet("", "", ""))

                Column {
                    Surface(color = MaterialTheme.colors.background) {
                        Greeting("Android")
                    }
                    Text("Contract address: ${contractAddress.value}", color = MaterialTheme.colors.onSurface)
                    Text("Greeting method result: ${contractAddress.value}", color = MaterialTheme.colors.onSurface)
                    Text("Wallet address: ${wallet.value.address}", color = MaterialTheme.colors.onSurface)
                    Text("Wallet mnemonic: ${wallet.value.mnemonic}", color = MaterialTheme.colors.onSurface)
                    Text("Wallet private key: ${wallet.value.privateKey}", color = MaterialTheme.colors.onSurface)
                    Button({
                        val password = "password"
                        viewModel.createWallet(password)
                    }) {
                        Text("Create wallet")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Web3JSampleTheme {
        Greeting("Android")
    }
}