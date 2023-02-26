package com.dogsteven.sellingapplication.presentation.screen.main.tag.analytic

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.main.tag.analytic.viewmodel.AnalyticViewModel

@Composable
fun AnalyticComposable(
    appNavController: AppNavController,
    viewModel: AnalyticViewModel = hiltViewModel()
) {
    EventHandlerComposable(appNavController = appNavController, viewModel = viewModel)
}