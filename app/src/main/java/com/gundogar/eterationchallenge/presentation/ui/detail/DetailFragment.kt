package com.gundogar.eterationchallenge.presentation.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.gundogar.eterationchallenge.databinding.FragmentDetailBinding
import com.gundogar.eterationchallenge.presentation.ui.base.BaseFragment


class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailFragmentArgs by navArgs()
        val productId = args.id
        binding.tvId.text = productId


    }
}