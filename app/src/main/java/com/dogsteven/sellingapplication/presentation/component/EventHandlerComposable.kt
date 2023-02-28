package com.dogsteven.sellingapplication.presentation.component

import androidx.compose.material.ScaffoldState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.dogsteven.sellingapplication.common.ApplicationEvent
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.util.BaseViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EventHandlerComposable(
    appNavController: AppNavController,
    viewModel: BaseViewModel,
    snackbarHostState: SnackbarHostState? = null
) {
    LaunchedEffect(true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is ApplicationEvent.UIEvent.NavigateTo -> {
                    val (route, builder) = event
                    appNavController.navigate(route, builder)
                }
                is ApplicationEvent.UIEvent.ShowSnackBar -> {
                    if (snackbarHostState != null) {
                        val (message, actionLabel) = event
                        snackbarHostState.showSnackbar(message, actionLabel)
                    }
                }
            }
        }
    }
}