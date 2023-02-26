package com.dogsteven.sellingapplication.presentation.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.RouteGraph
import kotlinx.coroutines.delay

@Composable
fun SplashComposable(
    appNavController: AppNavController
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Welcome to Selling App!")

        LaunchedEffect(true) {
            delay(1500)
            appNavController.navigate(RouteGraph.SignIn) {
                popUpTo(RouteGraph.Splash.destination) {
                    inclusive = true
                }
            }
        }
    }
}