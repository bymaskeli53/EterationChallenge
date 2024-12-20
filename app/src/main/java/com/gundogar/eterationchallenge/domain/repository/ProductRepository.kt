package com.gundogar.eterationchallenge.domain.repository

import androidx.paging.PagingData
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ApiResult
import kotlinx.coroutines.flow.Flow


/**
 * Repository interface for providing product data.
 * Can fetch products from remote or local data sources.
 */
interface ProductRepository {

    /**
     * Retrieves all products with paging support.
     *
     * @return A [Flow] emitting [PagingData] of [Product] items.
     */
    fun getAllProducts(): Flow<PagingData<Product>>

    /**
     * Fetches a product by its unique ID.
     *
     * @param id The product ID.
     * @return [ApiResult.Success] containing the product if found, otherwise [ApiResult.Error].
     */
    suspend fun getProductById(id: String): ApiResult<Product>

    /**
     * Searches products matching the provided query, with paging support.
     *
     * @param query The search keyword.
     * @return A [Flow] emitting [PagingData] of filtered products.
     */
    suspend fun searchProducts(query: String): Flow<PagingData<Product>>
}
