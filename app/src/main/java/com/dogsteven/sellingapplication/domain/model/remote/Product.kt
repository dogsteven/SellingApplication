package com.dogsteven.sellingapplication.domain.model.remote

data class Product(
    val id: Int,
    val name: String,
    val imageURL: String,
    val prices: List<Int>
)