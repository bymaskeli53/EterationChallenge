package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.domain.repository.CartRepository
import javax.inject.Inject

/**
 * Use case for adding a new item to the cart.
 *
 * @property cartRepository The repository that handles cart operations.
 */
class AddCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    /**
     * Adds the given [CartItem] to the cart.
     *
     * @param cartItem The item to be added to the cart.
     */
    suspend operator fun invoke(cartItem: CartItem) {
        return cartRepository.addToCart(cartItem = cartItem)
    }
}
