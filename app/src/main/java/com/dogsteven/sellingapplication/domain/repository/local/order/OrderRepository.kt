package com.dogsteven.sellingapplication.domain.repository.local.order

import com.dogsteven.sellingapplication.domain.dao.OrderDAO
import com.dogsteven.sellingapplication.domain.model.local.Order
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class OrderRepository  @Inject constructor(
    private val orderDAO: OrderDAO
) {
    fun getAllProductsFromDate(date: Date): Flow<List<Order.OrderWithSubOrders>> {
        return orderDAO.getAllOrdersFromDate(date)
    }

    suspend fun insertCompleteOrder(order: Order, suborders: List<Order.Suborder>): Long {
        return orderDAO.insertCompleteOrder(order, suborders)
    }

    suspend fun removeAllOrdersUntilDate(date: Date) {
        orderDAO.removeAllOrdersUntilDate(date)
    }
}