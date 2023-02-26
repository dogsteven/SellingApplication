package com.dogsteven.sellingapplication.navigation.implementation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.MainNavigationRoute
import com.dogsteven.sellingapplication.navigation.NavigationRoute
import com.dogsteven.sellingapplication.navigation.RootNavigationRoute

class AppNavControllerImpl(
    override val rootNavController: NavHostController,
    private val _mainNavController: NavHostController? = null
): AppNavController {
    override val mainNavController: NavHostController
        get() = _mainNavController!!

    private fun rootNavigate(route: RootNavigationRoute, builder: NavOptionsBuilder.() -> Unit) {
        rootNavController.navigate(route.destination, builder)
    }

    private fun mainNavigate(route: MainNavigationRoute, builder: NavOptionsBuilder.() -> Unit) {
        mainNavController.navigate(route.destination, builder)
    }

    override fun navigate(route: NavigationRoute, builder: NavOptionsBuilder.() -> Unit) {
        when (route) {
            is RootNavigationRoute -> rootNavigate(route, builder)
            is MainNavigationRoute -> mainNavigate(route, builder)
        }
    }

    override fun equip(mainNavController: NavHostController): AppNavController {
        return if (_mainNavController != null) {
            this
        } else {
            AppNavControllerImpl(rootNavController, mainNavController)
        }
    }
}