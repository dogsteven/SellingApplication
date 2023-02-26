package com.dogsteven.sellingapplication.presentation.screen.main.tag.management.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.dogsteven.sellingapplication.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManagementViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle
): BaseViewModel(application, savedStateHandle) {

}