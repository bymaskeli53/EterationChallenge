package com.gundogar.eterationchallenge.data.repository

import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ApiResult
import com.gundogar.eterationchallenge.data.remote.ProductService
import com.gundogar.eterationchallenge.data.remote.safeApiCall
import com.gundogar.eterationchallenge.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {
    override suspend fun getAllProducts(): ApiResult<List<Product>> {
        return safeApiCall { productService.getAllProducts() }
    }

    override suspend fun getProductById(id: String): ApiResult<Product> {
        return safeApiCall { productService.getProductById(id) }
    }
}