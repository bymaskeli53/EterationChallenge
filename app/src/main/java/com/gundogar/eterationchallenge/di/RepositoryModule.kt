package com.gundogar.eterationchallenge.di

import com.gundogar.eterationchallenge.data.repository.CartRepositoryImpl
import com.gundogar.eterationchallenge.data.repository.ProductRepositoryImpl
import com.gundogar.eterationchallenge.domain.repository.CartRepository
import com.gundogar.eterationchallenge.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindCartRepository(
        cartRepositoryImpl: CartRepositoryImpl
    ): CartRepository
}
