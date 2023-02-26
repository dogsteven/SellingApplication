package com.dogsteven.sellingapplication.navigation

import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder

interface AppNavController {
    val rootNavController: NavHostController
    val mainNavController: NavHostController

    fun navigate(route: NavigationRoute, builder: NavOptionsBuilder.() -> Unit)

    fun equip(mainNavController: NavHostController): AppNavController
}