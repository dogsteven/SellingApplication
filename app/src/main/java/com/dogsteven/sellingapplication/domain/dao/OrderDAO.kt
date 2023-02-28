package com.dogsteven.sellingapplication.domain.dao

import androidx.room.*
import com.dogsteven.sellingapplication.domain.model.local.Order
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface OrderDAO {
    @Transaction
    @Query("SELECT * FROM orders WHERE date >= :date;")
    fun getAllOrdersFromDate(date: Date): Flow<List<Order.OrderWithSubOrders>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubOrders(suborders: List<Order.Suborder>)

    suspend fun insertCompleteOrder(order: Order, suborders: List<Order.Suborder>): Long {
        val orderID = insertOrder(order)
        insertSubOrders(suborders.map { suborder -> suborder.copy(orderID = orderID) })
        return orderID
    }

    @Query("DELETE FROM orders WHERE date < :date;")
    suspend fun removeAllOrdersUntilDate(date: Date)
}