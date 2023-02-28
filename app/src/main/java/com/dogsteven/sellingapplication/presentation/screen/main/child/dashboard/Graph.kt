package com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard

import androidx.compose.material.ScaffoldState
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph

fun NavGraphBuilder.buildDashboardComposable(
    appNavController: AppNavController,
    snackbarHostState: SnackbarHostState
) {
    composable(RouteGraph.Main.Dashboard.destination) {
        DashboardComposable(
            appNavController = appNavController,
            snackbarHostState = snackbarHostState
        )
    }
}