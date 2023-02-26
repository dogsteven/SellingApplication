package com.dogsteven.sellingapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.dogsteven.sellingapplication.navigation.implementation.AppNavControllerImpl
import com.dogsteven.sellingapplication.presentation.screen.main.buildMainComposable
import com.dogsteven.sellingapplication.presentation.screen.sign_in.buildSignInComposable
import com.dogsteven.sellingapplication.presentation.screen.splash.buildSplashComposable

@Composable
fun NavigationComposable() {
    val rootNavController = rememberNavController()
    val appNavController = AppNavControllerImpl(rootNavController)

    NavHost(navController = appNavController.rootNavController, startDestination = RouteGraph.Splash.destination) {
        buildSplashComposable(appNavController)
        buildSignInComposable(appNavController)
        buildMainComposable(appNavController)
    }
}