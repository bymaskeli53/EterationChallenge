package com.gundogar.eterationchallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gundogar.eterationchallenge.data.model.Product
import com.gundogar.eterationchallenge.data.remote.ProductService

class ProductPagingSource(
    private val productService: ProductService
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentPage = params.key ?: 1
            val response = productService.getAllProducts(page = currentPage) // API isteği

            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { state.closestPageToPosition(it)?.prevKey?.plus(1) }
    }
}
