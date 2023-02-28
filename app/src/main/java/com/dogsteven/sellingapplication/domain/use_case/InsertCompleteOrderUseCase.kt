package com.dogsteven.sellingapplication.domain.use_case

import com.dogsteven.sellingapplication.common.Result
import com.dogsteven.sellingapplication.domain.model.local.Order
import com.dogsteven.sellingapplication.domain.repository.local.order.OrderRepository
import kotlinx.coroutines.delay
import java.util.*
import javax.inject.Inject

class InsertCompleteOrderUseCase @Inject constructor(
    private val orderRepository: OrderRepository
) {
    data class Request(val order: Order) {
        companion object {
            fun build(
                date: Date,
                byUserID: Int,
                builder: SubordersBuilder.() -> Unit
            ): Request {
                val subordersBuilder = SubordersBuilder()
                builder(subordersBuilder)
                return Order(date, byUserID, subordersBuilder.final).let(::Request)
            }
        }

        class SubordersBuilder {
            private val suborders: MutableList<Suborder> = mutableListOf()

            val final: List<Suborder> get() = suborders

            fun suborder(productID: Int, price: Int, amount: Int) {
                val suborder = Suborder(productID, price, amount)
                suborders.add(suborder)
            }
        }

        data class Order(
            val date: Date,
            val byUserID: Int,
            val suborders: List<Suborder>
        )

        data class Suborder(
            val productID: Int,
            val price: Int,
            val amount: Int
        )
    }

    data class Response(val id: Long)

    suspend fun execute(request: Request): Response {
        delay(2000)
        val order = Order(
            id = 0,
            date = request.order.date,
            byUserID = request.order.byUserID
        )

        val suborders: List<Order.Suborder> = request.order.suborders.map { requestSuborder ->
            requestSuborder.run {
                Order.Suborder(
                    id = 0,
                    orderID = 0,
                    productID = productID,
                    price = price,
                    amount = amount
                )
            }
        }

        val id = orderRepository.insertCompleteOrder(order, suborders)

        return Response(id)
    }
}