package com.dogsteven.sellingapplication.presentation.screen.main.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.dogsteven.sellingapplication.common.ApplicationEvent
import com.dogsteven.sellingapplication.navigation.RouteGraph
import com.dogsteven.sellingapplication.util.AppDataStore
import com.dogsteven.sellingapplication.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle
): BaseViewModel(application, savedStateHandle) {
    suspend fun signOut() {
        val appDataStore = AppDataStore(application.applicationContext)
        appDataStore.removeUser()

        val navigateToSignIn = ApplicationEvent.UIEvent.NavigateTo(RouteGraph.SignIn) {
            popUpTo(RouteGraph.Main.destination) {
                inclusive = true
            }
        }

        emitEvent(navigateToSignIn)
    }
}