package com.gundogar.eterationchallenge.data.remote

import com.gundogar.eterationchallenge.data.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    // Tüm ürünleri getirir
    @GET("products")
    suspend fun getAllProducts(): List<Product>

    // Belirli bir ürünü ID ile getirir
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): Product
}
