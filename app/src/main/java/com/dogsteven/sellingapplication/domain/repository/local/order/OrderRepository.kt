package com.dogsteven.sellingapplication.domain.repository.local.order

import com.dogsteven.sellingapplication.domain.dao.OrderDAO
import java.util.*
import javax.inject.Inject

class OrderRepository  @Inject constructor(
    private val orderDAO: OrderDAO
)