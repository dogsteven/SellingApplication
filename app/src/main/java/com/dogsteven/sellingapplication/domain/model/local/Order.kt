package com.dogsteven.sellingapplication.domain.model.local

import androidx.annotation.NonNull
import androidx.room.*
import com.dogsteven.sellingapplication.util.RoomDateTimeConverter
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true)
    val id: Long,
    val date: Date = Date().also(SimpleDateFormat.getDateInstance()::format),
    val byUserID: Int
) {
    @Entity(
        tableName = "suborders",
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
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        val orderID: Long,
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
    ) {
        val dictionary: Map<String, Any> get() = mapOf(
            "date" to order.date.time,
            "byUserID" to order.byUserID,
            "suborders" to suborders.map { suborder ->
                suborder.run {
                    mapOf(
                        "productID" to productID,
                        "price" to price,
                        "amount" to amount
                    )
                }
            }
        )
    }
}