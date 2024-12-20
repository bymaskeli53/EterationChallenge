package com.gundogar.eterationchallenge.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.domain.usecase.AddCartItemUseCase
import com.gundogar.eterationchallenge.domain.usecase.DeleteCartItemUseCase
import com.gundogar.eterationchallenge.domain.usecase.GetCartItemsUseCase
import com.gundogar.eterationchallenge.domain.usecase.GetTotalPriceUseCase
import com.gundogar.eterationchallenge.domain.usecase.UpdateCartItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val addCartItemUseCase: AddCartItemUseCase,
    private val getTotalPriceUseCase: GetTotalPriceUseCase,
    private val updateCartItemUseCase: UpdateCartItemUseCase,
    private val deleteCartItemUseCase: DeleteCartItemUseCase
) : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    /**
     * Explicit backing fields will be updated when it becomes stable
     */
    private val _totalPrice = MutableStateFlow<Double>(0.0)
    val totalPrice: StateFlow<Double> = _totalPrice

    fun loadCartItems() {
        viewModelScope.launch {
            getCartItemsUseCase().collect {
                _cartItems.value = it
            }
        }
    }

    fun getTotalPrice() {
        viewModelScope.launch {
            getTotalPriceUseCase().collect {
                _totalPrice.value = it
            }
        }
    }

    fun addToCart(cartItem: CartItem) {
        viewModelScope.launch {
            addCartItemUseCase(cartItem)
            loadCartItems()
        }
    }

    fun updateQuantity(id: String, quantity: Int) {
        viewModelScope.launch {
            updateCartItemUseCase(id, quantity)
            loadCartItems()
        }
    }

    fun deleteCartItem(id: String) {
        viewModelScope.launch {
            deleteCartItemUseCase(id)
            loadCartItems()
        }
    }
}
