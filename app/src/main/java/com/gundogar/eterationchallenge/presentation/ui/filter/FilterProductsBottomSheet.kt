package com.gundogar.eterationchallenge.presentation.ui.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.gundogar.eterationchallenge.databinding.FragmentProductsBottomSheetBinding
import com.gundogar.eterationchallenge.presentation.ui.filter.sort.SortAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterProductsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var sortAdapter: SortAdapter

    private var _binding: FragmentProductsBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        bottomSheet?.let {
            val behavior = BottomSheetBehavior.from(it)
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
            behavior.skipCollapsed = true
        }

        sortAdapter = SortAdapter(listOf("Option 1", "Option 2", "Option 3"))
        binding.sortByRv.adapter = sortAdapter

        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }


}