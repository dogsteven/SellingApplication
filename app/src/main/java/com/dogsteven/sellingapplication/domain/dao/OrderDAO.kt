package com.dogsteven.sellingapplication.domain.dao

import androidx.room.*
import com.dogsteven.sellingapplication.domain.model.local.Order

@Dao
interface OrderDAO {
    @Query("SELECT * FROM order WHERE date >= :date")
    suspend fun getAllOrderFromDate(date: Long): List<Order.OrderWithSubOrders>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubOrders(suborders: List<Order.Suborder>)

    suspend fun insertCompleteOrder(order: Order, suborders: List<Order.Suborder>) {
        val orderID = insertOrder(order)
        insertSubOrders(suborders.map { suborder -> suborder.copy(orderID = orderID) })
    }

    @Query("DELETE FROM order WHERE date < :date")
    suspend fun removeAllOrderUntilDate(date: Long)
}