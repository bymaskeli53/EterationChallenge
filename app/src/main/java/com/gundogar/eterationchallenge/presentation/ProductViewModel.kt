package com.gundogar.eterationchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.domain.usecase.GetAllProductsUseCase
import com.gundogar.eterationchallenge.domain.usecase.GetFilteredProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject


/**
 * ViewModel responsible for handling product listing and search operations.
 *
 * @property getProductsUseCase Use case for retrieving all products.
 * @property getFilteredProductsUseCase Use case for retrieving filtered products based on a query.
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetAllProductsUseCase,
    private val getFilteredProductsUseCase: GetFilteredProductsUseCase
) : ViewModel() {


    val products: Flow<PagingData<Product>> = getProductsUseCase()


    val searchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val filteredProducts: Flow<PagingData<Product>> = searchQuery
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                getProductsUseCase()
            } else {
                getFilteredProductsUseCase(query)
            }
        }
    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}
