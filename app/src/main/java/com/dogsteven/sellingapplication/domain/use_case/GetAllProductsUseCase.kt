package com.dogsteven.sellingapplication.domain.use_case

import com.dogsteven.sellingapplication.domain.model.remote.Product
import com.dogsteven.sellingapplication.domain.repository.remote.product.ProductRepository
import com.dogsteven.sellingapplication.common.Result
import com.dogsteven.sellingapplication.util.dummy.map
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    object Request

    data class Response(val products: List<Product>)

    suspend fun execute(request: Request): Result<Response> {
        val products = productRepository.getAllProducts()
        return products.map(::Response)
    }
}