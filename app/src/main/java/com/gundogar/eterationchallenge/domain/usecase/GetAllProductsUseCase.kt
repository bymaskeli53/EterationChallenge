package com.gundogar.eterationchallenge.domain.usecase

import androidx.paging.PagingData
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(): Flow<PagingData<Product>> {
        return repository.getAllProducts()
    }
}
