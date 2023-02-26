package com.dogsteven.sellingapplication.presentation.screen.sign_in.viewmodel

data class SignInState(
    val inputUsername: String = "",
    val inputPassword: String = "",
    val authenticationState: AuthenticationState = AuthenticationState.Idle
) {
    sealed interface AuthenticationState {
        object Idle: AuthenticationState
        object Loading: AuthenticationState
        object Success: AuthenticationState
        object Failed: AuthenticationState
    }
}