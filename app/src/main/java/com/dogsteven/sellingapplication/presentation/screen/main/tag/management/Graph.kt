package com.dogsteven.sellingapplication.presentation.screen.main.tag.management

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph

fun NavGraphBuilder.buildManagementComposable(appNavController: AppNavController) {
    composable(RouteGraph.Main.Management.destination) {
        ManagementComposable(appNavController)
    }
}