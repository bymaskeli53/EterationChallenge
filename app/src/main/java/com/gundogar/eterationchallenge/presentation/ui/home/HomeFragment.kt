package com.gundogar.eterationchallenge.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.gundogar.eterationchallenge.data.paging.ProductsLoadStateAdapter
import com.gundogar.eterationchallenge.databinding.FragmentHomeBinding
import com.gundogar.eterationchallenge.presentation.ProductViewModel
import com.gundogar.eterationchallenge.presentation.ui.base.BaseFragment
import com.gundogar.eterationchallenge.utils.hide
import com.gundogar.eterationchallenge.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: ProductViewModel by viewModels()
    private val productAdapter by lazy {
        ProductAdapter(onItemClicked = {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        })
    }

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
            adapter = productAdapter.withLoadStateHeaderAndFooter(
                header = ProductsLoadStateAdapter { productAdapter.retry() },
                footer = ProductsLoadStateAdapter { productAdapter.retry() }
            )
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collectLatest { pagingData ->
                    productAdapter.submitData(pagingData)
                }
            }
        }

//            productAdapter.addLoadStateListener { loadState ->
//                val isLoading = loadState.source.refresh is LoadState.Loading ||
//                        loadState.append is LoadState.Loading ||
//                        loadState.prepend is LoadState.Loading
//                binding.shimmerContainer.isVisible = isLoading
//                binding.rvProducts.isVisible = !isLoading
//
//                if (loadState.source.refresh is LoadState.Error) {
//                    Snackbar.make(binding.root, "Error loading data", Snackbar.LENGTH_SHORT).show()
//                }
//            }

        // LoadStateListener ile shimmer kontrolü
        productAdapter.addLoadStateListener { loadState ->
//                binding.rvProducts.isVisible = loadState.source.refresh is LoadState.NotLoading
//                binding.shimmerContainer.isVisible = loadState.source.refresh is LoadState.Loading
//                if (loadState.source.refresh is LoadState.Error) {
//                    Snackbar.make(binding.root, "Error loading data", Snackbar.LENGTH_SHORT).show()
//                }

            when (loadState.refresh) { // İlk yükleme durumunu kontrol et
                is LoadState.Loading -> {
                    binding.shimmerContainer.startShimmer()
                    binding.shimmerContainer.show()
                    binding.rvProducts.hide()
                    handleError(loadState)
                }

                is LoadState.NotLoading -> {
                    binding.shimmerContainer.stopShimmer()
                    binding.shimmerContainer.hide()
                    binding.rvProducts.isVisible = productAdapter.itemCount > 0
                    handleError(loadState)
                }

                is LoadState.Error -> {
                    binding.shimmerContainer.stopShimmer()
                    binding.shimmerContainer.hide()
                    binding.rvProducts.show()
                    Snackbar.make(binding.root, "Error loading data", Snackbar.LENGTH_SHORT).show()
                    handleError(loadState)
                }
            }
        }
    }

    private fun handleError(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(requireContext(), "${it.error}", Toast.LENGTH_LONG).show()
        }
    }
}
