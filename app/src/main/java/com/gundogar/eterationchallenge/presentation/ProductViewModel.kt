package com.gundogar.eterationchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.domain.usecase.GetAllProductsUseCase
import com.gundogar.eterationchallenge.domain.usecase.GetFilteredProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetAllProductsUseCase,
    private val getFilteredProductsUseCase: GetFilteredProductsUseCase
) : ViewModel() {

//    val products: Flow<PagingData<Product>> = getProductsUseCase()
//        .cachedIn(viewModelScope) // Cache the data in ViewModel's scope
    val products: Flow<PagingData<Product>> = getProductsUseCase()

    val searchQuery = MutableStateFlow("")

    val filteredProducts: Flow<PagingData<Product>> = searchQuery
        .debounce(300) // Kullanıcının yazmayı bitirmesi için bekle
        .distinctUntilChanged() // Aynı sorgular için işleme gerek yok
        .flatMapLatest { query ->
            if (query.isEmpty()) {
                getProductsUseCase() // Tüm ürünler
            } else {
                getFilteredProductsUseCase(query) // Filtrelenmiş ürünler
            }
        }
        .cachedIn(viewModelScope)

//    private fun fetchProducts() {
//        viewModelScope.launch {
//            _products.value = ApiResult.Loading
//            _products.value = getProductsUseCase()
//        }
//    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}
