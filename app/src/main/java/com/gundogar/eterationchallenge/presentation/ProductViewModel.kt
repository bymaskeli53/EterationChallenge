package com.gundogar.eterationchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ApiResult
import com.gundogar.eterationchallenge.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

//    val products: Flow<PagingData<Product>> = getProductsUseCase()
//        .cachedIn(viewModelScope) // Cache the data in ViewModel's scope
    val products: Flow<PagingData<Product>> = getProductsUseCase()

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts



//    private fun fetchProducts() {
//        viewModelScope.launch {
//            _products.value = ApiResult.Loading
//            _products.value = getProductsUseCase()
//        }
//    }
}
