package com.gundogar.eterationchallenge.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gundogar.eterationchallenge.data.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartItem>>

    @Query("UPDATE cart_items SET quantity = :quantity WHERE id = :id")
    suspend fun updateCartItemQuantity(id: String, quantity: Int)

    @Query("SELECT SUM(price * quantity) FROM cart_items")
    fun getTotalPrice(): Flow<Double>

    @Query("DELETE FROM cart_items WHERE id = :id")
    suspend fun deleteCartItem(id: String)

    /**
     * To get number of items and show in badge
     */
    @Query("SELECT COUNT(*) FROM cart_items")
    fun getBasketItemCount(): Flow<Int>
}
