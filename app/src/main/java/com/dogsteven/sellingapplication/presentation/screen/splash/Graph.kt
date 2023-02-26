package com.dogsteven.sellingapplication.presentation.screen.splash

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph

fun NavGraphBuilder.buildSplashComposable(
    appNavController: AppNavController
) {
    composable(RouteGraph.Splash.destination) {
        SplashComposable(appNavController)
    }
}