package com.gundogar.eterationchallenge


import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gundogar.eterationchallenge.data.local.AppDatabase
import com.gundogar.eterationchallenge.data.local.CartDao
import com.gundogar.eterationchallenge.data.model.CartItem
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith




@RunWith(AndroidJUnit4::class)
class CartDaoTest {

    @get:Rule
    val instantTaskExecutorRule = MainDispatcherRule()

    private lateinit var database: AppDatabase
    private lateinit var cartDao: CartDao

    @Before
    fun setup() {
        // In-memory database oluşturulur, her testten sonra veriler silinir.
        database = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        cartDao = database.cartDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertCartItem_insertsItem() = runBlocking {
        val item = CartItem(id = "1", name = "Apple", price = "10.0", quantity = 1)
        cartDao.insertCartItem(item)

        val items = cartDao.getAllCartItems().first()
        assertEquals(1, items.size)
        assertEquals(item, items[0])
    }

    @Test
    fun deleteCartItem_deletesItem() = runBlocking {
        val item = CartItem(id = "1", name = "Banana", price = "5.0", quantity = 2)
        cartDao.insertCartItem(item)

        cartDao.deleteCartItem(item)
        val items = cartDao.getAllCartItems().first()
        assertTrue(items.isEmpty())
    }

    @Test
    fun updateCartItemQuantity_updatesQuantity() = runBlocking {
        val item = CartItem(id = "1", name = "Orange", price = "2.0", quantity = 1)
        cartDao.insertCartItem(item)

        cartDao.updateCartItemQuantity("1", 5)
        val items = cartDao.getAllCartItems().first()
        assertEquals(5, items[0].quantity)
    }

    @Test
    fun deleteCartItemById_deletesTheCorrectItem() = runBlocking {
        val item1 = CartItem(id = "1", name = "Apple", price = "10.0", quantity = 1)
        val item2 = CartItem(id = "2", name = "Banana", price = "5.0", quantity = 2)
        cartDao.insertCartItem(item1)
        cartDao.insertCartItem(item2)

        cartDao.deleteCartItem("1")
        val items = cartDao.getAllCartItems().first()

        assertEquals(1, items.size)
        assertEquals("2", items[0].id)
    }

    @Test
    fun getBasketItemCount_returnsNumberOfItems() = runBlocking {
        val item1 = CartItem(id = "1", name = "Peach", price = "2.0", quantity = 2)
        val item2 = CartItem(id = "2", name = "Plum", price = "2.5", quantity = 1)
        cartDao.insertCartItem(item1)
        cartDao.insertCartItem(item2)

        val count = cartDao.getBasketItemCount().first()
        assertEquals(2, count)
    }
}
