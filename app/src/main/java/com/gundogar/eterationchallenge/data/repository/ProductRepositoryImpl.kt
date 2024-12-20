package com.gundogar.eterationchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.paging.ProductPagingSource
import com.gundogar.eterationchallenge.data.remote.ApiResult
import com.gundogar.eterationchallenge.data.remote.ProductService
import com.gundogar.eterationchallenge.data.remote.safeApiCall
import com.gundogar.eterationchallenge.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

private const val PAGE_SIZE = 4
private const val INITIAL_LOAD_SIZE = 4

class ProductRepositoryImpl @Inject constructor(
    private val productService: ProductService
) : ProductRepository {
//    override suspend fun getAllProducts(): ApiResult<List<Product>> {
//        return safeApiCall { productService.getAllProducts() }
//    }

    // TODO: Paging header ve footer eklenecek

    override fun getAllProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = INITIAL_LOAD_SIZE),
            pagingSourceFactory = { ProductPagingSource(productService) }
        ).flow
    }

    override suspend fun getProductById(id: String): ApiResult<Product> {
        return safeApiCall { productService.getProductById(id) }
    }

    override suspend fun searchProducts(query: String): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(pageSize = 4, initialLoadSize = 4),
            pagingSourceFactory = { ProductPagingSource(productService, query) }
        ).flow
    }
}
