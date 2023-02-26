package com.dogsteven.sellingapplication.domain.model.local

import androidx.room.*
import com.dogsteven.sellingapplication.util.RoomDateTimeConverter
import java.util.*

@Entity(tableName = "order")
data class Order(
    @PrimaryKey
    val id: Int,
    @TypeConverters(RoomDateTimeConverter::class)
    val date: Date
) {
    @Entity(
        tableName = "suborder",
        foreignKeys = [
            ForeignKey(
                entity = Order::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("orderID"),
                onDelete = ForeignKey.CASCADE
            )
        ]
    )
    data class Suborder(
        @PrimaryKey
        val id: Int,
        val orderID: Int,
        val productID: Int,
        val price: Int,
        val amount: Int
    )

    data class OrderWithSubOrders(
        @Embedded
        val order: Order,
        @Relation(
            parentColumn = "id",
            entityColumn = "orderID"
        )
        val suborders: List<Suborder>
    )
}