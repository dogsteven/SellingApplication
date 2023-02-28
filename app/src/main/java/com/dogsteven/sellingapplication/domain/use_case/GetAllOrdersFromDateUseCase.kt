package com.dogsteven.sellingapplication.domain.use_case

import com.dogsteven.sellingapplication.domain.model.local.Order
import com.dogsteven.sellingapplication.domain.repository.local.order.OrderRepository
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class GetAllOrdersFromDateUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    data class Request(val date: Date)

    data class Response(val listOfOrdersFlow: Flow<List<Order.OrderWithSubOrders>>)

    fun execute(request: Request): Response {
        val date = request.date
        val listOfOrdersFlow = orderRepository.getAllProductsFromDate(date)
        return Response(listOfOrdersFlow)
    }
}