package com.linh.web3jsample.presentation

import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument
import com.linh.web3jsample.R

object NavigationDirections {
    val createWallet = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

        override val destination: String
            get() = "create_wallet"

        override val isBottomNavigationItem: Boolean = false

        override val screenNameRes: Int
            get() = R.string.all_wallet
    }

    val home = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

        override val destination: String
            get() = "home"

        override val isBottomNavigationItem: Boolean = true

        override val screenNameRes: Int
            get() = R.string.all_home
    }

    val wallet = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

        override val destination: String
            get() = "wallet"

        override val isBottomNavigationItem: Boolean = true

        override val screenNameRes: Int
            get() = R.string.all_wallet
    }

    val main = object : NavigationCommand {
        override val arguments: List<NamedNavArgument>
            get() = emptyList()

        override val destination: String
            get() = "main"

        override val isBottomNavigationItem: Boolean = false

        override val screenNameRes: Int
            get() = R.string.all_home
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

        override val isBottomNavigationItem: Boolean = false

        override val screenNameRes: Int
            get() = R.string.app_name
    }
}

interface NavigationCommand {
    val arguments: List<NamedNavArgument>
    val destination: String
    val isBottomNavigationItem: Boolean
    val screenNameRes: Int
}