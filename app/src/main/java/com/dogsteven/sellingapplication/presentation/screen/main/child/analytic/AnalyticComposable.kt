package com.dogsteven.sellingapplication.presentation.screen.main.child.analytic

import androidx.compose.material.ScaffoldState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.main.child.analytic.viewmodel.AnalyticViewModel
import com.dogsteven.sellingapplication.util.AppDataStore

@Composable
fun AnalyticComposable(
    appNavController: AppNavController,
    snackbarHostState: SnackbarHostState,
    viewModel: AnalyticViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val appDataStore = AppDataStore(context)
    val userFromDataStore by appDataStore.currentUser.collectAsState(initial = null)

    val user = if (userFromDataStore == null) {
        return
    } else {
        userFromDataStore!!
    }

    EventHandlerComposable(
        appNavController = appNavController,
        viewModel = viewModel,
        snackbarHostState = snackbarHostState
    )
}