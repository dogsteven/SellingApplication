package com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph

fun NavGraphBuilder.buildDashboardComposable(appNavController: AppNavController) {
    composable(RouteGraph.Main.Dashboard.destination) {
        DashboardComposable(appNavController)
    }
}