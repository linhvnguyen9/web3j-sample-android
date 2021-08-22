package com.linh.web3jsample.presentation

import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {
    val commands = MutableStateFlow(NavigationDirections.default)

    fun navigate(directions: NavigationCommand) {
        commands.value = directions
    }
}