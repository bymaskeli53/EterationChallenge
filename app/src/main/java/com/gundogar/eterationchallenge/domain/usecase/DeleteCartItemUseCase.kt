package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.domain.repository.CartRepository
import javax.inject.Inject

class DeleteCartItemUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(id: String) {
        cartRepository.deleteCartItem(id)
    }
}