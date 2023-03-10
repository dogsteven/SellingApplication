package com.dogsteven.sellingapplication.presentation.screen.sign_in.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.dogsteven.sellingapplication.common.ApplicationEvent
import com.dogsteven.sellingapplication.util.BaseViewModel
import com.dogsteven.sellingapplication.domain.use_case.AuthenticateUserUseCase
import com.dogsteven.sellingapplication.navigation.RouteGraph
import com.dogsteven.sellingapplication.util.AppDataStore
import com.dogsteven.sellingapplication.util.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val authenticateUserUseCase: AuthenticateUserUseCase,
): BaseViewModel(application, savedStateHandle) {
    private val _state: MutableStateFlow<SignInState> = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> = _state

    fun setInputUsername(value: String) {
        _state.value = _state.value.copy(inputUsername = value)
    }

    fun setInputPassword(value: String) {
        _state.value = _state.value.copy(inputPassword = value)
    }

    fun setAuthenticationState(authenticationState: SignInState.AuthenticationState) {
        _state.value = _state.value.copy(authenticationState = authenticationState)
    }

    fun setIsSaveUsernameAndPassword(value: Boolean) {
        _state.value = _state.value.copy(isSaveUsernameAndPassword = value)
    }

    suspend fun getSavedUsernameAndPasswordFromAppDataStore() {
        val appDataStore = AppDataStore(context = application.applicationContext)
        appDataStore.currentSavedUsernameAndPassword.collectLatest { savedUsernameAndPassword ->
            if (savedUsernameAndPassword != null) {
                val (savedUsername, savedPassword) = savedUsernameAndPassword
                setInputUsername(savedUsername)
                setInputPassword(savedPassword)
            }
        }
    }

    private val useCasesResultHandlers = object {
        val AuthenticateUser = object: ResultHandler<AuthenticateUserUseCase.Response> {
            override suspend fun onSuccess(value: AuthenticateUserUseCase.Response) {
                setAuthenticationState(SignInState.AuthenticationState.Success)
                val appDataStore = AppDataStore(context = application.applicationContext)

                if (_state.value.isSaveUsernameAndPassword) {
                    appDataStore.saveSavedUsernameAndPassword(
                        username = value.user.username,
                        password = value.user.password
                    )
                } else {
                    appDataStore.removeSavedUsernameAndPassword()
                }

                appDataStore.saveUser(value.user)

                val navigateToMain = ApplicationEvent.UIEvent.NavigateTo(RouteGraph.Main) {
                    popUpTo(RouteGraph.SignIn.destination) {
                        inclusive = true
                    }
                }

                emitEvent(navigateToMain)
            }

            override suspend fun onFailure(exception: Throwable) {
                setAuthenticationState(SignInState.AuthenticationState.Failed)
            }
        }
    }

    fun authenticate() {
        executeJob("authenticate") {
            setAuthenticationState(SignInState.AuthenticationState.Loading)

            val request = AuthenticateUserUseCase.Request(
                username = state.value.inputUsername,
                password = state.value.inputPassword
            )

            val response = authenticateUserUseCase.execute(request)

            useCasesResultHandlers.AuthenticateUser(response)
        }
    }
}