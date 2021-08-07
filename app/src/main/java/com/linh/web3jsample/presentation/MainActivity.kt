package com.linh.web3jsample.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.linh.web3jsample.presentation.theme.Web3JSampleTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Web3JSampleTheme {
                // A surface container using the 'background' color from the theme
                val contractAddress = viewModel.contractAddress.collectAsState("")

                Column {
                    Surface(color = MaterialTheme.colors.background) {
                        Greeting("Android")
                    }
                    Text("Contract address: ${contractAddress.value}", color = Color.White)
                    Text("Greeting method result: ${contractAddress.value}", color = Color.White)
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