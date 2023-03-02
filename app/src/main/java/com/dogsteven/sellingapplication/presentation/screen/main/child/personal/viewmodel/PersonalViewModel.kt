package com.dogsteven.sellingapplication.presentation.screen.main.child.personal.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.dogsteven.sellingapplication.util.AppDataStore
import com.dogsteven.sellingapplication.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PersonalViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle
): BaseViewModel(application, savedStateHandle) {
    private val _state: MutableStateFlow<PersonalState> = MutableStateFlow(PersonalState())
    val state: StateFlow<PersonalState> = _state

    fun setInputUsername(value: String) {
        _state.value = _state.value.copy(inputUsername = value)
    }

    fun setInputPassword(value: String) {
        _state.value = _state.value.copy(inputPassword = value)
    }

    fun setInputFirstname(value: String) {
        _state.value = _state.value.copy(inputFirstname = value)
    }

    fun setInputLastname(value: String) {
        _state.value = _state.value.copy(inputLastname = value)
    }

    fun setInputPhone(value: String) {
        _state.value = _state.value.copy(inputPhone = value)
    }
}