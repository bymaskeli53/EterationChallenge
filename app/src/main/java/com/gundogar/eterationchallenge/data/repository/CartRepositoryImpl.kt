package com.gundogar.eterationchallenge.data.repository

import com.gundogar.eterationchallenge.data.local.CartDao
import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao
) : CartRepository {
    override suspend fun addToCart(cartItem: CartItem) {
        cartDao.insertCartItem(cartItem)
    }

    override suspend fun removeFromCart(cartItem: CartItem) {
        cartDao.deleteCartItem(cartItem)
    }

    override fun getCartItems(): Flow<List<CartItem>> {
        return cartDao.getAllCartItems()
    }

    override suspend fun updateCartItemQuantity(id: String, quantity: Int) {
        cartDao.updateCartItemQuantity(id, quantity)
    }
}
