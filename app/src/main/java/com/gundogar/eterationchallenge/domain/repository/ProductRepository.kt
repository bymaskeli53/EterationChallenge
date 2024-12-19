package com.gundogar.eterationchallenge.domain.repository

import androidx.paging.PagingData
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ApiResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<PagingData<Product>>

    suspend fun getProductById(id: String): ApiResult<Product>
}
