package com.dogsteven.sellingapplication.common

import androidx.navigation.NavOptionsBuilder
import com.dogsteven.sellingapplication.navigation.NavigationRoute

sealed interface ApplicationEvent {
    interface UIEvent: ApplicationEvent {
        data class NavigateTo(val route: NavigationRoute, val builder: NavOptionsBuilder.() -> Unit): UIEvent
    }
}