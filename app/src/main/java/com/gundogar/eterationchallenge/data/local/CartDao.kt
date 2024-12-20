package com.gundogar.eterationchallenge.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gundogar.eterationchallenge.data.model.CartItem
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) for managing cart data.
 * Interacts with the Room database to handle cart operations.
 */
@Dao
interface CartDao {
    /**
     * Inserts or updates the given [CartItem] in the cart.
     *
     * @param cartItem The item to insert or update.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    /**
     * Deletes the specified [CartItem] from the cart.
     *
     * @param cartItem The item to delete.
     */
    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    /**
     * Retrieves all cart items as a flow.
     *
     * @return A [Flow] emitting a list of all items in the cart.
     */
    @Query("SELECT * FROM cart_items")
    fun getAllCartItems(): Flow<List<CartItem>>

    /**
     * Updates the quantity of a cart item with the specified ID.
     *
     * @param id The ID of the item to update.
     * @param quantity The new quantity.
     */
    @Query("UPDATE cart_items SET quantity = :quantity WHERE id = :id")
    suspend fun updateCartItemQuantity(id: String, quantity: Int)

    /**
     * Calculates the total price of all items in the cart.
     * If `price` is stored as a string in the database, use CAST in the SQL query.
     *
     * @return A [Flow] emitting the sum of all item prices times their quantities.
     */
    @Query("SELECT SUM(price * quantity) FROM cart_items")
    fun getTotalPrice(): Flow<Double>

    /**
     * Deletes the cart item with the specified ID.
     *
     * @param id The ID of the item to delete.
     */
    @Query("DELETE FROM cart_items WHERE id = :id")
    suspend fun deleteCartItem(id: String)

    /**
     * We get count number of items and show in badge on bottom navigation
     */
    @Query("SELECT COUNT(*) FROM cart_items")
    fun getBasketItemCount(): Flow<Int>
}
