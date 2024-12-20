package com.gundogar.eterationchallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ProductService

class ProductPagingSource(
    private val productService: ProductService,
    private val query: String? = null
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: 1
            val products = if (query.isNullOrEmpty()) {
                productService.getAllProducts(page)
            } else {
                productService.searchProducts(page, query)
            }

            LoadResult.Page(
                data = products,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (products.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }
}
