package com.gundogar.eterationchallenge.presentation.ui.filter

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gundogar.eterationchallenge.R
import com.gundogar.eterationchallenge.databinding.FragmentProductsBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FilterProductsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentProductsBottomSheetBinding? = null
    private val binding get() = _binding!!

  //  private val viewModel: FilterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                // Bottom sheet'i tam ekran yapmak için yüksekliğini MATCH_PARENT olarak ayarla
                val layoutParams = it.layoutParams
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                it.layoutParams = layoutParams

                // Behavior'ı tam ekran olarak ayarla
                val behavior = BottomSheetBehavior.from(it)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
                behavior.isFitToContents = false // İçerik boyutuna göre küçülmesin
                behavior.skipCollapsed = true   // Collapse durumunu atla
                behavior.isDraggable = true    // İstenirse draggable olsun
            }
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.closeButton.setOnClickListener {
            dismiss()
        }
        setupSortOptions()
        //setupApplyButton()
    }


    private fun setupSortOptions() {
        binding.sortByGroup.setOnCheckedChangeListener { _, checkedId ->
            val sortOption = when (checkedId) {
                R.id.sortOldToNew -> "price"
                R.id.sortNewToOld -> "price"
                R.id.sortPriceHighToLow -> "price"
                R.id.sortPriceLowToHigh -> "price"
                else -> null
            }
           // viewModel.updateSortOption(sortOption)
        }
    }

//    private fun setupBrandFilter() {
//        val brandAdapter = FilterAdapter { brand, isChecked ->
//            viewModel.updateBrandFilter(brand, isChecked)
//        }
//        binding.brandRecyclerView.adapter = brandAdapter
//        brandAdapter.submitList(viewModel.getAvailableBrands())
//    }
//
//    private fun setupModelFilter() {
//        val modelAdapter = FilterAdapter { model, isChecked ->
//            viewModel.updateModelFilter(model, isChecked)
//        }
//        binding.modelRecyclerView.adapter = modelAdapter
//        modelAdapter.submitList(viewModel.getAvailableModels())
//    }

//    private fun setupApplyButton() {
//        binding.applyFiltersButton.setOnClickListener {
//            viewModel.applyFilters()
//            dismiss()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}