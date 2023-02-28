package com.dogsteven.sellingapplication.presentation.screen.main.child.dashboard.viewmodel

import com.dogsteven.sellingapplication.domain.model.remote.Product

data class DashboardState(
    val products: List<Product> = listOf()
)