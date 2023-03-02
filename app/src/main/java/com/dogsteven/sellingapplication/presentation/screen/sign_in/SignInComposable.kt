package com.dogsteven.sellingapplication.presentation.screen.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogsteven.sellingapplication.common.ApplicationEvent
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.sign_in.viewmodel.SignInState
import com.dogsteven.sellingapplication.presentation.screen.sign_in.viewmodel.SignInViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInComposable(
    appNavController: AppNavController,
    viewModel: SignInViewModel = hiltViewModel()
) {
    EventHandlerComposable(appNavController = appNavController, viewModel = viewModel)

    LaunchedEffect(true) {
        viewModel.getSavedUsernameAndPasswordFromAppDataStore()
    }

    val state by viewModel.state.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8f.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = state.inputUsername,
                onValueChange = viewModel::setInputUsername,
                placeholder = { Text(text = "Username") }
            )

            TextField(
                value = state.inputPassword,
                onValueChange = viewModel::setInputPassword,
                placeholder = { Text(text = "Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Checkbox(
                    checked = state.isSaveUsernameAndPassword,
                    onCheckedChange = viewModel::setIsSaveUsernameAndPassword
                )

                Text(text = "Save username and password?")
            }

            Button(
                onClick = viewModel::authenticate,
                enabled = !(state.authenticationState is SignInState.AuthenticationState.Loading || state.authenticationState is SignInState.AuthenticationState.Success)
            ) {
                Text(
                    text = when (state.authenticationState) {
                        is SignInState.AuthenticationState.Idle -> "Sign in"
                        is SignInState.AuthenticationState.Loading -> "Authenticating..."
                        is SignInState.AuthenticationState.Success -> "Success"
                        is SignInState.AuthenticationState.Failed -> "Authentication failed! Try again!"
                    }
                )
            }
        }
    }
}