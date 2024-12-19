package com.gundogar.eterationchallenge.data.paging

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class ProductsLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<ProductsLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: ProductsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ProductsLoadStateViewHolder {
        return ProductsLoadStateViewHolder.create(parent, retry)
    }
}
