package com.gundogar.eterationchallenge.presentation

import androidx.lifecycle.ViewModel
import com.gundogar.eterationchallenge.domain.usecase.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductDetailUseCase: GetProductDetailUseCase
) : ViewModel() {
    suspend fun getProductDetail(productId: String) = getProductDetailUseCase(productId)

}