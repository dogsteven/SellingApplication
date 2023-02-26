package com.dogsteven.sellingapplication.presentation.screen.main.tag.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.main.tag.dashboard.viewmodel.DashboardViewModel

@Composable
fun DashboardComposable(
    appNavController: AppNavController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    EventHandlerComposable(appNavController = appNavController, viewModel = viewModel)

    Text(text = "Welcome to dashboard!")
}