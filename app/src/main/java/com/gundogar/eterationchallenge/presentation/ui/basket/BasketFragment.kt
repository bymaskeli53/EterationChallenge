package com.gundogar.eterationchallenge.presentation.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gundogar.eterationchallenge.databinding.FragmentBasketBinding
import com.gundogar.eterationchallenge.presentation.BasketAdapter
import com.gundogar.eterationchallenge.presentation.CartViewModel
import com.gundogar.eterationchallenge.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BasketFragment : BaseFragment<FragmentBasketBinding>() {

    private val viewModel: CartViewModel by viewModels()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBasketBinding {
        return FragmentBasketBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadCartItems()
        viewModel.getTotalPrice()
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cartItems.collectLatest {
                    val adapter = BasketAdapter(onQuantityChanged = { id, quantity ->
                        viewModel.updateQuantity(id, quantity)
                        viewModel.getTotalPrice()
                    },
                        onItemDeleted = { id ->
                            viewModel.deleteCartItem(id)
                        })
                    adapter.submitList(it)

                    binding.rvProducts.adapter = adapter

                    viewModel.totalPrice.collectLatest {
                        binding.tvPrice.text = String.format("%.2f ₺", it)
                    }
                }
            }
        }

    }
}
