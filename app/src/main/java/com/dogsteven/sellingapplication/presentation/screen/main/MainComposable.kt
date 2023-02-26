package com.dogsteven.sellingapplication.presentation.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.dogsteven.sellingapplication.presentation.screen.main.tag.analytic.buildAnalyticComposable
import com.dogsteven.sellingapplication.presentation.screen.main.tag.dashboard.buildDashboardComposable
import com.dogsteven.sellingapplication.presentation.screen.main.tag.management.buildManagementComposable
import com.dogsteven.sellingapplication.presentation.screen.main.viewmodel.MainViewModel
import com.dogsteven.sellingapplication.util.AppDataStore
import kotlinx.coroutines.launch

@Composable
fun MainComposable(
    appNavController: AppNavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    EventHandlerComposable(appNavController = appNavController, viewModel = viewModel)

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val appDataStore = AppDataStore(context)
    val user by appDataStore.getCurrentUser.collectAsState(initial = null)

    if (user != null) {
        val signedInUser = user!!

        val visibleRoutes: List<MainNavigationRoute> = listOf(RouteGraph.Main.Dashboard, RouteGraph.Main.Analytic, RouteGraph.Main.Management)
            .filter { route ->
                route.permissions.any(signedInUser.permissions::contains)
            }

        val mainNavBackStackEntry by appNavController.mainNavController.currentBackStackEntryAsState()
        val currentDestination = mainNavBackStackEntry?.destination

        val currentMainRoute: MainNavigationRoute? = visibleRoutes.firstOrNull { route -> currentDestination?.route == route.destination }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = currentMainRoute?.name ?: "") },
                    actions = {
                        IconButton(onClick = {
                            scope.launch {
                                viewModel.signOut()
                            }
                        }) {
                            Icon(Icons.Rounded.ExitToApp, contentDescription = null)
                        }
                    }
                )
            },
            bottomBar = {
                BottomNavigation {
                    for (route in visibleRoutes) {
                        BottomNavigationItem(
                            icon = { Icon(ImageVector.vectorResource(route.icon), contentDescription = null) },
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
                buildDashboardComposable(appNavController)
                buildAnalyticComposable(appNavController)
                buildManagementComposable(appNavController)
            }
        }
    }
}