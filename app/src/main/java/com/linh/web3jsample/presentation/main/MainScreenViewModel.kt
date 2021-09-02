package com.linh.web3jsample.presentation.main

import androidx.lifecycle.ViewModel
import com.linh.web3jsample.presentation.NavigationDirections
import com.linh.web3jsample.presentation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val navigationManager: NavigationManager
) : ViewModel() {
    fun onClickBottomNavigationHome() {
        navigationManager.navigate(NavigationDirections.home)
    }

    fun onClickBottomNavigationWallet() {
        navigationManager.navigate(NavigationDirections.wallet)
    }
}