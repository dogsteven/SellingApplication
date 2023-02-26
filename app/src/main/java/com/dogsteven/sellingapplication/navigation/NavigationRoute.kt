package com.dogsteven.sellingapplication.navigation

import com.dogsteven.sellingapplication.domain.model.remote.User

sealed interface NavigationRoute {
    val destination: String
}

sealed class RootNavigationRoute(override val destination: String): NavigationRoute
sealed class MainNavigationRoute(
    override val destination: String,
    val icon: Int,
    val name: String,
    val permissions: List<User.Permission>
): NavigationRoute