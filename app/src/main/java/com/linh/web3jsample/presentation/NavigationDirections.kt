package com.linh.web3jsample.presentation

import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

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

object TokenDetailNavigation {
    const val KEY_TOKEN_ID = "tokenId"

    val route = "detail/{$KEY_TOKEN_ID}"

    val args : List<NamedNavArgument>
        get() = listOf(
            navArgument(KEY_TOKEN_ID) { type = NavType.LongType }
        )

    fun detail(tokenId: Long) = object : NavigationCommand {
        override val arguments = args

        override val destination: String
            get() = "detail/$tokenId"
    }
}

interface NavigationCommand {
    val arguments: List<NamedNavArgument>

    val destination: String
}