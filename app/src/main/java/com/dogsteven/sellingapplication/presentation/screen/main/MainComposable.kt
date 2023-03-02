package com.dogsteven.sellingapplication.presentation.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.navigation.MainNavigationRoute
import com.dogsteven.sellingapplication.navigation.RouteGraph
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.main.child.analytic.buildAnalyticComposable
import com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard.buildDashboardComposable
import com.dogsteven.sellingapplication.presentation.screen.main.child.management.buildManagementComposable
import com.dogsteven.sellingapplication.presentation.screen.main.child.personal.buildPersonalComposable
import com.dogsteven.sellingapplication.presentation.screen.main.viewmodel.MainViewModel
import com.dogsteven.sellingapplication.util.AppDataStore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable(
    appNavController: AppNavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val appDataStore = AppDataStore(context)
    val userFromDataStore by appDataStore.currentUser.collectAsState(initial = null)
    val user = if (userFromDataStore == null) {
        return
    } else {
        userFromDataStore!!
    }

    val state by viewModel.state.collectAsState()

    val visibleRoutes: List<MainNavigationRoute> = remember(user.permissions) {
        RouteGraph.Main.children
            .filter { route ->
                route.permissions.any(user.permissions::contains)
            }
    }

    val mainNavBackStackEntry by appNavController.mainNavController.currentBackStackEntryAsState()
    val currentDestination = mainNavBackStackEntry?.destination

    val currentMainRoute: MainNavigationRoute? = visibleRoutes.firstOrNull { route -> currentDestination?.route == route.destination }

    val snackbarHostState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    EventHandlerComposable(
        appNavController = appNavController,
        viewModel = viewModel,
        snackbarHostState = snackbarHostState
    )

    if (state.isShowSignOutDialog) {
        val signOutScope = rememberCoroutineScope()

        AlertDialog(
            onDismissRequest = viewModel::hideSignOutDialog,
            title = {
                Text(text = "Are you sure?")
            },
            text = {
                Text(text = "Are you sure you want to sign out?")
            },
            confirmButton = {
                TextButton(onClick = {
                    signOutScope.launch {
                        viewModel.signOut()
                    }
                }) {
                    Text(text = "Sign out")
                }
            }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                title = { Text(text = currentMainRoute?.name ?: "") },
                actions = {
                    IconButton(onClick = viewModel::showSignOutDialog) {
                        Icon(Icons.Rounded.ExitToApp, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                for (route in visibleRoutes) {
                    NavigationBarItem(
                        selected = currentMainRoute == route,
                        onClick = {
                            appNavController.navigate(route) {
                                popUpTo(appNavController.mainNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = route.icon),
                                contentDescription = null
                            )
                        },
                        label = { Text(text = route.name) }
                    )
                }
            }
        },
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPaddings ->
        NavHost(
            navController = appNavController.mainNavController,
            startDestination = visibleRoutes[0].destination,
            modifier = Modifier.padding(innerPaddings)
        ) {
            buildDashboardComposable(appNavController, snackbarHostState)
            buildAnalyticComposable(appNavController, snackbarHostState)
            buildManagementComposable(appNavController, snackbarHostState)
            buildPersonalComposable(appNavController, snackbarHostState)
        }
    }
}