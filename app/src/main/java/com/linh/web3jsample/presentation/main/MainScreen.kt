package com.linh.web3jsample.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.linh.web3jsample.R
import com.linh.web3jsample.presentation.NavigationDirections
import com.linh.web3jsample.presentation.NavigationManager
import com.linh.web3jsample.presentation.TokenDetailNavigation
import com.linh.web3jsample.presentation.home.HomeScreen
import com.linh.web3jsample.presentation.tokendetail.TokenDetailScreen
import com.linh.web3jsample.presentation.wallet.WalletScreen

@Composable
fun MainScreen(navigationManager: NavigationManager, mainScreenViewModel: MainScreenViewModel) {
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(NavigationDirections.home, NavigationDirections.wallet)

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavigationItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = {
                            val icon = when (screen) {
                                NavigationDirections.home -> painterResource(R.drawable.ic_baseline_home_24)
                                NavigationDirections.wallet -> painterResource(R.drawable.ic_baseline_account_balance_wallet_24)
                                else -> painterResource(R.drawable.ic_baseline_home_24)
                            }
                            Icon(icon, null)
                        },
                        label = { Text(stringResource(screen.screenNameRes)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.destination } == true,
                        onClick = {
                            when(screen) {
                                NavigationDirections.home -> mainScreenViewModel.onClickBottomNavigationHome()
                                NavigationDirections.wallet -> mainScreenViewModel.onClickBottomNavigationWallet()
                            }
                        }
                    )
                }
            }
        }
    ) {
        innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            NavHost(
                navController,
                startDestination = NavigationDirections.home.destination
            ) {
                composable(NavigationDirections.home.destination) {
                    HomeScreen(
                        hiltViewModel()
                    )
                }
                composable(NavigationDirections.wallet.destination) {
                    WalletScreen(
                        hiltViewModel()
                    )
                }
                composable(TokenDetailNavigation.route, TokenDetailNavigation.args) {
                    TokenDetailScreen(
                        hiltViewModel(),
                        it.arguments?.getLong(TokenDetailNavigation.KEY_TOKEN_ID) ?: 0L
                    )
                }
            }
        }

        navigationManager.commands.collectAsState().value.also { command ->
            if (command.destination.isNotEmpty() && command.isBottomNavigationItem) {
                navController.navigate(command.destination)
            }
        }
    }
}