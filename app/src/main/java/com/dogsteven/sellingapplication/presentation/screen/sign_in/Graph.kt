package com.dogsteven.sellingapplication.presentation.screen.sign_in

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph

fun NavGraphBuilder.buildSignInComposable(appNavController: AppNavController) {
    composable(RouteGraph.SignIn.destination) {
        SignInComposable(appNavController)
    }
}