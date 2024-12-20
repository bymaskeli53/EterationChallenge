package com.gundogar.eterationchallenge

import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.data.repository.FakeCartRepository
import com.gundogar.eterationchallenge.domain.usecase.AddCartItemUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AddCartItemUseCaseTest {

    private lateinit var fakeCartRepository: FakeCartRepository
    private lateinit var addCartItemUseCase: AddCartItemUseCase

    @Before
    fun setup() {
        // Fake repository örneği
        fakeCartRepository = FakeCartRepository()
        // Use case'e fake repository enjekte ediliyor
        addCartItemUseCase = AddCartItemUseCase(fakeCartRepository)
    }

    @Test
    fun `invoke should add item to cart`() = runTest {
        val cartItem = CartItem(id = "1", name = "Test Item", price = "10.0", quantity = 1)

        // Use case çağırılıyor
        addCartItemUseCase(cartItem)

        // Repository'den güncel sepet öğelerini al
        val items = fakeCartRepository.getCartItems().first()

        // Sepetteki ürün sayısı 1 ve eklenen ürünün kendisi olmalı
        assertEquals(1, items.size)
        assertEquals(cartItem, items[0])
    }

    @Test
    fun `invoke should add multiple items if called multiple times`() = runTest {
        val cartItem1 = CartItem(id = "1", name = "Test Item 1", price = "10.0", quantity = 1)
        val cartItem2 = CartItem(id = "2", name = "Test Item 2", price = "5.0", quantity = 2)

        addCartItemUseCase(cartItem1)
        addCartItemUseCase(cartItem2)

        val items = fakeCartRepository.getCartItems().first()

        assertEquals(2, items.size)
        assertEquals(cartItem1, items[0])
        assertEquals(cartItem2, items[1])
    }
}
