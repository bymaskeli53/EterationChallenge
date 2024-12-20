package com.gundogar.eterationchallenge.domain.usecase

import androidx.paging.PagingData
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Use case for retrieving all products with paging support.
 *
 * @property repository The repository that provides product data.
 */
class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    /**
     * Returns a [Flow] of [PagingData] containing all products.
     *
     * @return A flow emitting pages of product data.
     */
    operator fun invoke(): Flow<PagingData<Product>> {
        return repository.getAllProducts()
    }
}
