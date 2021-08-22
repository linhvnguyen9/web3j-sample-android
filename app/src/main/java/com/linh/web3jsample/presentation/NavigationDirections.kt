package com.linh.web3jsample.presentation

import androidx.navigation.compose.NamedNavArgument

object NavigationDirections {
    val createWallet = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

        override val destination: String
            get() = "create_wallet"
    }

    val home = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

        override val destination: String
            get() = "home"
    }

    val default = createWallet
}

interface NavigationCommand {
    val arguments: List<NamedNavArgument>

    val destination: String
}