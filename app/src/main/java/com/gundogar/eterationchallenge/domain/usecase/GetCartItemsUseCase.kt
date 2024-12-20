package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCartItemsUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    operator fun invoke(): Flow<List<CartItem>> {
        return cartRepository.getCartItems()
    }
}
