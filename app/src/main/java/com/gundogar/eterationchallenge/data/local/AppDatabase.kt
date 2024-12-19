package com.gundogar.eterationchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gundogar.eterationchallenge.data.model.CartItem

@Database(entities = [CartItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}
