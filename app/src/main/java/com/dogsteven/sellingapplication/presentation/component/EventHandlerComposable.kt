package com.dogsteven.sellingapplication.presentation.component

import androidx.compose.material.ScaffoldState
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
    scaffoldState: ScaffoldState? = null
) {
    LaunchedEffect(true) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is ApplicationEvent.UIEvent.NavigateTo -> {
                    val (route, builder) = event
                    appNavController.navigate(route, builder)
                }
                is ApplicationEvent.UIEvent.ShowSnackBar -> {
                    if (scaffoldState != null) {
                        val (message, actionLabel) = event
                        scaffoldState.snackbarHostState.showSnackbar(message, actionLabel)
                    }
                }
            }
        }
    }
}