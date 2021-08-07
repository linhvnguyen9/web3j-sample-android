package com.linh.web3jsample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.linh.web3jsample.ui.theme.Web3JSampleTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import org.web3j.protocol.core.methods.response.Web3ClientVersion
import java.math.BigInteger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val web3 = Web3j.build(HttpService("http://192.168.1.109:8545"))
        val credentials = Credentials.create("fc03b915f2dc9c1ab6136e2a7328a68405d04ca261365b558c82a27d6029a6a9")
        try {
            GlobalScope.launch {
                val clientVersion = web3.web3ClientVersion().sendAsync().get()
                val contract = HelloWorld.deploy(
                    web3,
                    credentials,
                    BigInteger("20000000000", 10),
                    BigInteger("6721975", 10),
                    "Hello Blockchain World!"
                ).send()
                Log.d("MainActivity", "Contract address: " + contract.getContractAddress());
                Log.d("MainActivity", "Greeting method result:" + contract.greeting().send());
            }
        } catch (e: Exception) {
            Log.e("MainActivity", e.toString())
        }

        setContent {
            Web3JSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
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