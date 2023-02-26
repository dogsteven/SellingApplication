package com.dogsteven.sellingapplication.presentation.screen.main.tag.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.dogsteven.sellingapplication.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle
): BaseViewModel(application, savedStateHandle) {
    private val _state: MutableStateFlow<DashboardState> = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state


}