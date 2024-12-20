package com.gundogar.eterationchallenge.data.repository

import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeCartRepository : CartRepository {
    private val cartItems = mutableListOf<CartItem>()
    private val cartItemsFlow = MutableStateFlow<List<CartItem>>(emptyList())

    override suspend fun addToCart(cartItem: CartItem) {
        cartItems.add(cartItem)
        cartItemsFlow.value = cartItems.toList()
    }

    override suspend fun removeFromCart(cartItem: CartItem) { /* Implement if needed */ }
    override fun getCartItems(): Flow<List<CartItem>> = cartItemsFlow
    override suspend fun updateCartItemQuantity(id: String, quantity: Int) { /* Implement if needed */ }
    override fun getTotalPrice(): Flow<Double> { /* Implement if needed */ return flowOf(0.0) }
    override suspend fun deleteCartItem(id: String) { /* Implement if needed */ }
    override fun getBasketItemCount(): Flow<Int> { /* Implement if needed */ return flowOf(cartItems.size) }
}
