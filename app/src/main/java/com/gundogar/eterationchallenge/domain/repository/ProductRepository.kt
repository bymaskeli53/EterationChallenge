package com.gundogar.eterationchallenge.domain.repository

import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ApiResult

interface ProductRepository {

    suspend fun getAllProducts(): ApiResult<List<Product>>

    suspend fun getProductById(id: String): ApiResult<Product>
}