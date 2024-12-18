package com.gundogar.eterationchallenge.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.gundogar.eterationchallenge.data.remote.ApiResult
import com.gundogar.eterationchallenge.data.remote.ApiResult.Loading
import com.gundogar.eterationchallenge.data.remote.ApiResult.Success
import com.gundogar.eterationchallenge.databinding.FragmentHomeBinding
import com.gundogar.eterationchallenge.presentation.ProductViewModel
import com.gundogar.eterationchallenge.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: ProductViewModel by viewModels()
    private val productAdapter by lazy { ProductAdapter(onItemClicked = {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
        findNavController().navigate(action)
    }) }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = productAdapter
            setHasFixedSize(true)
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect { state ->
                    when (state) {
                        is Loading -> {
                            showShimmerEffect(true)
                        }

                        is Success -> {
                            showShimmerEffect(false)
                            productAdapter.submitList(state.data)
                        }

                        is ApiResult.Error -> {
                            showShimmerEffect(false)
                            state.message?.let { message ->
                                Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun showShimmerEffect(show: Boolean) {
        if (show) {
            binding.shimmerContainer.startShimmer()
            binding.shimmerContainer.visibility = View.VISIBLE
            binding.rvProducts.visibility = View.GONE
        } else {
            binding.shimmerContainer.stopShimmer()
            binding.shimmerContainer.visibility = View.GONE
            binding.rvProducts.visibility = View.VISIBLE
        }
    }

}

