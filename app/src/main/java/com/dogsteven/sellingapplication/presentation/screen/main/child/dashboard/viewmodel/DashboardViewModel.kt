package com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.dogsteven.sellingapplication.domain.model.local.Order
import com.dogsteven.sellingapplication.domain.use_case.GetAllOrdersFromDateUseCase
import com.dogsteven.sellingapplication.domain.use_case.GetAllProductsUseCase
import com.dogsteven.sellingapplication.util.BaseViewModel
import com.dogsteven.sellingapplication.util.ResultHandler
import com.dogsteven.sellingapplication.util.eraseTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    savedStateHandle: SavedStateHandle,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getAllOrdersFromDateUseCase: GetAllOrdersFromDateUseCase
): BaseViewModel(application, savedStateHandle) {
    private val _state: MutableStateFlow<DashboardState> = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state

    val orders: Flow<List<Order.OrderWithSubOrders>> = run {
        val request = GetAllOrdersFromDateUseCase.Request(Calendar.getInstance().time.eraseTime)
        val response = getAllOrdersFromDateUseCase.execute(request)
        response.listOfOrdersFlow
    }

    private val useCasesResultHandlers = object {
        val GetAllProducts = object: ResultHandler<GetAllProductsUseCase.Response> {
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

            useCasesResultHandlers.GetAllProducts(response)
        }
    }
}