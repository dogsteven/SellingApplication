package com.dogsteven.sellingapplication.presentation.screen.main.child.analytic

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph

fun NavGraphBuilder.buildAnalyticComposable(appNavController: AppNavController) {
    composable(RouteGraph.Main.Analytic.destination) {
        AnalyticComposable(appNavController)
    }
}