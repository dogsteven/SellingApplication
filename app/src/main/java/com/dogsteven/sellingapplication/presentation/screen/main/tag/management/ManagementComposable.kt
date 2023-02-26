package com.dogsteven.sellingapplication.presentation.screen.main.tag.management

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.main.tag.management.viewmodel.ManagementViewModel

@Composable
fun ManagementComposable(
    appNavController: AppNavController,
    viewModel: ManagementViewModel = hiltViewModel()
) {
    EventHandlerComposable(appNavController = appNavController, viewModel = viewModel)

}