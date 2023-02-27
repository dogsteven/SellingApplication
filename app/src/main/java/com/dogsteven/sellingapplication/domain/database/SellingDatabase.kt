package com.dogsteven.sellingapplication.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dogsteven.sellingapplication.domain.dao.OrderDAO
import com.dogsteven.sellingapplication.domain.model.local.Order
import com.dogsteven.sellingapplication.util.RoomDateTimeConverter

@Database(
    entities = [Order::class, Order.Suborder::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomDateTimeConverter::class)
abstract class SellingDatabase: RoomDatabase() {
    abstract fun getOrderDAO(): OrderDAO
}