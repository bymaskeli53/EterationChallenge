package com.gundogar.eterationchallenge.domain.repository

import com.gundogar.eterationchallenge.data.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addToCart(cartItem: CartItem)
    suspend fun removeFromCart(cartItem: CartItem)
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun updateCartItemQuantity(id: String, quantity: Int)
    fun getTotalPrice(): Flow<Double>
    suspend fun deleteCartItem(id: String)
}
