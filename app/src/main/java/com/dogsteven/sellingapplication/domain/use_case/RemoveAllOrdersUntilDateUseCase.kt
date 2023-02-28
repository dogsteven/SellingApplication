package com.dogsteven.sellingapplication.domain.use_case

import com.dogsteven.sellingapplication.common.Result
import com.dogsteven.sellingapplication.domain.repository.local.order.OrderRepository
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

class RemoveAllOrdersUntilDateUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    data class Request(val date: Date)

    object Response

    suspend fun execute(request: Request): Response {
        delay(2000)
        val date = request.date
        orderRepository.removeAllOrdersUntilDate(date)
        return Response
    }
}