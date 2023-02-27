package com.dogsteven.sellingapplication.presentation.screen.main.child.analytic.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.dogsteven.sellingapplication.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnalyticViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle
): BaseViewModel(application, savedStateHandle) {

}