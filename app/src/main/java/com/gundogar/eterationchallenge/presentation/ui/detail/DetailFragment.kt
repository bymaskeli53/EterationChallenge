package com.gundogar.eterationchallenge.presentation.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.gundogar.eterationchallenge.data.remote.ApiResult
import com.gundogar.eterationchallenge.data.remote.ApiResult.Success
import com.gundogar.eterationchallenge.databinding.FragmentDetailBinding
import com.gundogar.eterationchallenge.presentation.DetailViewModel
import com.gundogar.eterationchallenge.presentation.MainActivity
import com.gundogar.eterationchallenge.presentation.ProductViewModel
import com.gundogar.eterationchallenge.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var productId: String

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailFragmentArgs by navArgs()
        productId = args.id


        observeViewModel()





        (requireActivity() as MainActivity).updateToolbarTitle("Product Details")
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
             val state =viewModel.getProductDetail(productId)
                when (state) {
                    is ApiResult.Loading -> {

                    }
                    is Success -> {
                        binding.tvPrice.text = state.data.price
                        binding.tvProductDescription.text = state.data.description
                        binding.tvProductTitle.text = state.data.model

                    }


                    is ApiResult.Error -> {}
                }
            }
        }
    }
}