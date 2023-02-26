package com.dogsteven.sellingapplication.domain.repository.product

import com.dogsteven.sellingapplication.domain.model.remote.Product

interface ProductRepository {
    suspend fun getProductByID(id: Int): Result<Product>
    suspend fun getAllProducts(): Result<List<Product>>

    suspend fun createProduct(
        name: String,
        imageURL: String,
        prices: List<Int>
    )

    suspend fun updateProduct(
        id: Int,
        name: String,
        imageURL: String,
        prices: List<Int>
    )

    suspend fun removeProduct(id: Int)
}