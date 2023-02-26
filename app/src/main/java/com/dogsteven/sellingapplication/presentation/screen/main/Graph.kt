package com.dogsteven.sellingapplication.presentation.screen.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph

fun NavGraphBuilder.buildMainComposable(appNavController: AppNavController) {
    composable(RouteGraph.Main.destination) {
        val mainNavController = rememberNavController()
        MainComposable(appNavController.equip(mainNavController))
    }
}