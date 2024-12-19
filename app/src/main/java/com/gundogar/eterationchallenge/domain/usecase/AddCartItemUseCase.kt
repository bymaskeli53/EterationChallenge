package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.domain.repository.CartRepository
import javax.inject.Inject

class AddCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(cartItem: CartItem) {
        return cartRepository.addToCart(cartItem = cartItem)
    }
}