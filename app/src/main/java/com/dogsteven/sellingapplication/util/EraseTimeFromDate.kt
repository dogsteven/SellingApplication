package com.dogsteven.sellingapplication.util

import java.util.*

val Date.eraseTime: Date get() = Calendar.getInstance().let { calendar ->
    calendar.time = this
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    calendar.time
}