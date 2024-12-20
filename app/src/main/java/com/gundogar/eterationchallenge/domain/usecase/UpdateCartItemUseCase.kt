package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.domain.repository.CartRepository
import javax.inject.Inject

class UpdateCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(id: String, quantity: Int) {
        cartRepository.updateCartItemQuantity(id, quantity)
    }
}
