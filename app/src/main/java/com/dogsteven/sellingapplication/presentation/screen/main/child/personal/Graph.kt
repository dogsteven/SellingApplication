package com.dogsteven.sellingapplication.presentation.screen.main.child.personal

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph

fun NavGraphBuilder.buildPersonalComposable(
    appNavController: AppNavController,
    snackbarHostState: SnackbarHostState
) {
    composable(RouteGraph.Main.Personal.destination) {
        PersonalComposable(
            appNavController = appNavController,
            snackbarHostState = snackbarHostState
        )
    }
}