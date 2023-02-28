package com.dogsteven.sellingapplication.util.dummy

import com.dogsteven.sellingapplication.common.Result
import com.dogsteven.sellingapplication.domain.model.remote.Product
import com.dogsteven.sellingapplication.domain.repository.remote.product.ProductRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class DummyProductRepository @Inject constructor(
    private val dummyProductDatabase: DummyProductDatabase
): ProductRepository {
    override suspend fun getProductByID(id: Int): Result<Product> {
        delay(2000)
        for (product in dummyProductDatabase.products) {
            if (product.id == id) {
                return Result.Success(product)
            }
        }
        return Result.Failure(Throwable("No product with id $id"))
    }

    override suspend fun getAllProducts(): Result<List<Product>> {
        delay(2000)
        return Result.Success(dummyProductDatabase.products)
    }

    override suspend fun createProduct(
        name: String,
        imageURL: String,
        prices: List<Int>
    ): Result<Product> {
        delay(2000)
        val id = (dummyProductDatabase.products.map { it.id }.maxOrNull() ?: -1) + 1
        val product = Product(
            id = id,
            name = name,
            imageURL = imageURL,
            prices = prices.distinct()
        )
        dummyProductDatabase.products.add(product)
        return Result.Success(product)
    }

    override suspend fun updateProduct(
        id: Int,
        name: String,
        imageURL: String,
        prices: List<Int>
    ): Result<Product> {
        delay(2000)
        val product = Product(
            id = id,
            name = name,
            imageURL = imageURL,
            prices = prices.distinct()
        )

        for (i in 0 until dummyProductDatabase.products.size) {
            if (dummyProductDatabase.products[i].id == id) {
                dummyProductDatabase.products[i] = product

                return Result.Success(product)
            }
        }
        return Result.Failure(Throwable("No product with id $id"))
    }

    override suspend fun removeProduct(id: Int): Result<Product> {
        for (i in 0 until dummyProductDatabase.products.size) {
            if (dummyProductDatabase.products[i].id == id) {
                val product = dummyProductDatabase.products[i]
                dummyProductDatabase.products.removeAt(i)
                return Result.Success(product)
            }
        }
        return Result.Failure(Throwable("No product with id $id"))
    }
}