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
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linh.web3jsample.domain.entity.Wallet
import com.linh.web3jsample.presentation.createwallet.CreateWalletScreen
import com.linh.web3jsample.presentation.home.HomeScreen
import com.linh.web3jsample.presentation.theme.Web3JSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModels()

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            Web3JSampleTheme {
                NavHost(
                    navController,
                    startDestination = NavigationDirections.createWallet.destination
                ) {
                    composable(NavigationDirections.createWallet.destination) {
                        CreateWalletScreen(
                            hiltViewModel(
                                navController.getBackStackEntry(
                                    NavigationDirections.createWallet.destination
                                )
                            )
                        )
                    }
                    composable(NavigationDirections.home.destination) {
                        HomeScreen(
                            hiltViewModel(
                                navController.getBackStackEntry(
                                    NavigationDirections.home.destination
                                )
                            )
                        )
                    }
                }

                navigationManager.commands.collectAsState().value.also { command ->
                    if (command.destination.isNotEmpty()) navController.navigate(command.destination)
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