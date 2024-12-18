package com.gundogar.eterationchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ApiResult
import com.gundogar.eterationchallenge.domain.usecase.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetAllProductsUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<ApiResult<List<Product>>>(ApiResult.Loading)
    val products: StateFlow<ApiResult<List<Product>>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _products.value = ApiResult.Loading
            _products.value = getProductsUseCase()
        }
    }
}