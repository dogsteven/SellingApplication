package com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard.viewmodel.DashboardViewModel
import com.dogsteven.sellingapplication.util.AppDataStore

@Composable
fun DashboardComposable(
    appNavController: AppNavController,
    snackbarHostState: SnackbarHostState,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val appDataStore = AppDataStore(context)
    val userFromDataStore by appDataStore.currentUser.collectAsState(initial = null)
    val state by viewModel.state.collectAsState()

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

    LaunchedEffect(true) {
        viewModel.getAllProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        
    }
}