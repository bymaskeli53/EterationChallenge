package com.gundogar.eterationchallenge.domain.repository

import com.gundogar.eterationchallenge.data.model.CartItem

interface CartRepository {
    suspend fun addToCart(cartItem: CartItem)
    suspend fun removeFromCart(cartItem: CartItem)
    suspend fun getCartItems(): List<CartItem>
    suspend fun updateCartItemQuantity(id: String, quantity: Int)
}
