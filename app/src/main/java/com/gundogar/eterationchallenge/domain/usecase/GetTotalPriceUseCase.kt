package com.gundogar.eterationchallenge.domain.usecase

import com.gundogar.eterationchallenge.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTotalPriceUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(): Flow<Double> {
        return cartRepository.getTotalPrice()
    }
}
