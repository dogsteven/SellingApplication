package com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.dogsteven.sellingapplication.common.ApplicationEvent
import com.dogsteven.sellingapplication.domain.repository.remote.product.ProductRepository
import com.dogsteven.sellingapplication.domain.use_case.AuthenticateUserUseCase
import com.dogsteven.sellingapplication.domain.use_case.GetAllProductsUseCase
import com.dogsteven.sellingapplication.navigation.RouteGraph
import com.dogsteven.sellingapplication.presentation.screen.sign_in.viewmodel.SignInState
import com.dogsteven.sellingapplication.util.AppDataStore
import com.dogsteven.sellingapplication.util.BaseViewModel
import com.dogsteven.sellingapplication.util.ResultHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val getAllProductsUseCase: GetAllProductsUseCase
): BaseViewModel(application, savedStateHandle) {
    private val _state: MutableStateFlow<DashboardState> = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state

    private val useCasesResultHandlers = object {
        val GetALLProducts = object: ResultHandler<GetAllProductsUseCase.Response> {
            override suspend fun onSuccess(value: GetAllProductsUseCase.Response) {
                _state.value = _state.value.copy(products = value.products)
            }

            override suspend fun onFailure(exception: Throwable) {

            }
        }
    }

    suspend fun getAllProducts() {
        executeJob("get all products") {
            val request = GetAllProductsUseCase.Request

            val response = getAllProductsUseCase.execute(request)

            useCasesResultHandlers.GetALLProducts(response)
        }
    }
}