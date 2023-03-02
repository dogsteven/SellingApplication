package com.dogsteven.sellingapplication.navigation

import com.dogsteven.sellingapplication.R
import com.dogsteven.sellingapplication.domain.model.remote.User

object RouteGraph {
    object Splash: RootNavigationRoute(destination = "splash")
    object SignIn: RootNavigationRoute(destination = "sign-in")
    object Main: RootNavigationRoute(destination = "main") {
        val children: List<MainNavigationRoute> = listOf(
            Dashboard, Analytic, Management, Personal
        )

        object Dashboard: MainNavigationRoute(
            destination = "main/dashboard",
            icon = R.drawable.ic_round_dashboard_24,
            name = "Dashboard",
            permissions = listOf(User.Permission.Employee)
        )

        object Analytic: MainNavigationRoute(
            destination = "main/analytic",
            icon = R.drawable.ic_round_auto_graph_24,
            name = "Analytic",
            permissions = listOf(User.Permission.Administrator)
        )

        object Management: MainNavigationRoute(
            destination = "main/management",
            icon = R.drawable.ic_round_settings_24,
            name = "Management",
            permissions = listOf(User.Permission.Administrator)
        )

        object Personal: MainNavigationRoute(
            destination = "main/personal",
            icon = R.drawable.ic_round_person_24,
            name = "Personal",
            permissions = listOf(User.Permission.Administrator, User.Permission.Employee)
        )
    }
}