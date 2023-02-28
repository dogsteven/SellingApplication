package com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard.viewmodel.DashboardViewModel
import com.dogsteven.sellingapplication.util.AppDataStore

@Composable
fun DashboardComposable(
    appNavController: AppNavController,
    scaffoldState: ScaffoldState,
    viewModel: DashboardViewModel = hiltViewModel()
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
        scaffoldState = scaffoldState
    )

    LaunchedEffect(true) {
        viewModel.getAllProducts()
    }
}