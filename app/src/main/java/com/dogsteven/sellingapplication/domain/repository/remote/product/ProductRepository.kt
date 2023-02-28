package com.dogsteven.sellingapplication.domain.repository.remote.product

import com.dogsteven.sellingapplication.domain.model.remote.Product
import com.dogsteven.sellingapplication.common.Result

interface ProductRepository {
    suspend fun getProductByID(id: Int): Result<Product>

    suspend fun getAllProducts(): Result<List<Product>>

    suspend fun createProduct(
        name: String,
        imageURL: String,
        prices: List<Int>
    ): Result<Product>

    suspend fun updateProduct(
        id: Int,
        name: String,
        imageURL: String,
        prices: List<Int>
    ): Result<Product>

    suspend fun removeProduct(id: Int): Result<Product>
}