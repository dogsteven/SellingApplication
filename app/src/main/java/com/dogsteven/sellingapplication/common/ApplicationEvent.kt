package com.dogsteven.sellingapplication.common

import androidx.navigation.NavOptionsBuilder
import com.dogsteven.sellingapplication.navigation.NavigationRoute

sealed interface ApplicationEvent {
    sealed interface UIEvent: ApplicationEvent {
        data class NavigateTo(
            val route: NavigationRoute,
            val builder: NavOptionsBuilder.() -> Unit
        ): UIEvent

        data class ShowSnackBar(
            val message: String,
            val actionLabel: String? = null
        ): UIEvent
    }
}