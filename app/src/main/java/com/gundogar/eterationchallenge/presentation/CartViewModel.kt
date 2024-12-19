package com.gundogar.eterationchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.domain.usecase.AddCartItemUseCase
import com.gundogar.eterationchallenge.domain.usecase.GetCartItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addCartItemUseCase: AddCartItemUseCase,
) : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun loadCartItems() {
        viewModelScope.launch {
            _cartItems.value = getCartItemsUseCase()
        }
    }

    fun addToCart(cartItem: CartItem) {
        viewModelScope.launch {
            addCartItemUseCase(cartItem)
            loadCartItems()
        }
    }
}
