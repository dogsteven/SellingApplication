package com.dogsteven.sellingapplication.presentation.screen.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
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
import com.dogsteven.sellingapplication.presentation.screen.main.viewmodel.MainViewModel
import com.dogsteven.sellingapplication.util.AppDataStore
import kotlinx.coroutines.launch

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

    val scaffoldState = rememberScaffoldState()
    val visibleRoutes: List<MainNavigationRoute> = listOf(RouteGraph.Main.Dashboard, RouteGraph.Main.Analytic, RouteGraph.Main.Management)
        .filter { route ->
            route.permissions.any(user.permissions::contains)
        }

    val mainNavBackStackEntry by appNavController.mainNavController.currentBackStackEntryAsState()
    val currentDestination = mainNavBackStackEntry?.destination

    val currentMainRoute: MainNavigationRoute? = visibleRoutes.firstOrNull { route -> currentDestination?.route == route.destination }

    EventHandlerComposable(
        appNavController = appNavController,
        viewModel = viewModel,
        scaffoldState = scaffoldState
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
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .padding(horizontal = 8f.dp)
                        .fillMaxWidth()
                ) {
                    TextButton(onClick = {
                        signOutScope.launch {
                            viewModel.signOut()
                        }
                    }) {
                        Text(text = "Sign out")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = currentMainRoute?.name ?: "") },
                actions = {
                    IconButton(onClick = viewModel::showSignOutDialog) {
                        Icon(Icons.Rounded.ExitToApp, contentDescription = null)
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation {
                for (route in visibleRoutes) {
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                ImageVector.vectorResource(route.icon),
                                contentDescription = null
                            )
                        },
                        label = { Text(text = route.name) },
                        selected = currentMainRoute == route,
                        onClick = {
                            appNavController.navigate(route) {
                                popUpTo(appNavController.mainNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPaddings ->
        NavHost(
            navController = appNavController.mainNavController,
            startDestination = visibleRoutes[0].destination,
            modifier = Modifier.padding(innerPaddings)
        ) {
            buildDashboardComposable(appNavController, scaffoldState)
            buildAnalyticComposable(appNavController, scaffoldState)
            buildManagementComposable(appNavController, scaffoldState)
        }
    }
}