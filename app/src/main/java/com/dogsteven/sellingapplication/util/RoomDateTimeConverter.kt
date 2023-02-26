package com.dogsteven.sellingapplication.util

import androidx.room.TypeConverter
import java.util.*

class RoomDateTimeConverter {
    @TypeConverter
    fun fromDate(value: Date): Long {
        return value.time
    }

    @TypeConverter
    fun toDate(value: Long): Date {
        return Date(value)
    }
}