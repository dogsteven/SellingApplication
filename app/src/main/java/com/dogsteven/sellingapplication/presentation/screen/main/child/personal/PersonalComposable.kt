package com.dogsteven.sellingapplication.presentation.screen.main.child.personal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dogsteven.sellingapplication.domain.model.remote.User
import com.dogsteven.sellingapplication.navigation.AppNavController
import com.dogsteven.sellingapplication.presentation.component.EventHandlerComposable
import com.dogsteven.sellingapplication.presentation.screen.main.child.personal.viewmodel.PersonalViewModel
import com.dogsteven.sellingapplication.util.AppDataStore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalComposable(
    appNavController: AppNavController,
    snackbarHostState: SnackbarHostState,
    viewModel: PersonalViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val appDataStore = AppDataStore(context)
    val userFromDataStore by appDataStore.currentUser.collectAsState(initial = null)
    val state by viewModel.state.collectAsState()

    val user = if (userFromDataStore == null) {
        return
    } else {
        userFromDataStore!!
    }

    EventHandlerComposable(
        appNavController = appNavController,
        viewModel = viewModel,
        snackbarHostState = snackbarHostState
    )

    LaunchedEffect(true) {
        viewModel.setInputUsername(user.username)
        viewModel.setInputPassword(user.password)
        viewModel.setInputFirstname(user.firstname)
        viewModel.setInputLastname(user.lastname)
        viewModel.setInputPhone(user.phone)
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(8f.dp, Alignment.CenterVertically),
        modifier = Modifier
            .padding(32f.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Update profile",
            style = MaterialTheme.typography.headlineMedium
        )

        TextField(
            value = state.inputUsername,
            onValueChange = viewModel::setInputUsername,
            placeholder = { Text(text = "Username") },
            label = { Text(text = "Username") },
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = state.inputPassword,
            onValueChange = viewModel::setInputPassword,
            placeholder = { Text(text = "Password") },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = state.inputFirstname,
            onValueChange = viewModel::setInputFirstname,
            placeholder = { Text(text = "Firstname") },
            label = { Text(text = "Firstname") },
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = state.inputUsername,
            onValueChange = viewModel::setInputLastname,
            placeholder = { Text(text = "Lastname") },
            label = { Text(text = "Lastname") },
            modifier = Modifier
                .fillMaxWidth()
        )

        TextField(
            value = state.inputPhone,
            onValueChange = viewModel::setInputPhone,
            placeholder = { Text(text = "Phone") },
            label = { Text(text = "Phone") },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}