package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketItemCountUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(): Flow<Int> {
        return cartRepository.getBasketItemCount()
    }
}
