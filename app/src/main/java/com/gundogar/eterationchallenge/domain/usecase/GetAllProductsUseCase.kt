package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ApiResult
import com.gundogar.eterationchallenge.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): ApiResult<List<Product>> {
        return repository.getAllProducts()
    }
}
