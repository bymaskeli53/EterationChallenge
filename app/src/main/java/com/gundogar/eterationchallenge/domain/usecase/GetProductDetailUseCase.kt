package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ApiResult
import com.gundogar.eterationchallenge.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: String): ApiResult<Product> {
        return productRepository.getProductById(productId)
    }
}
