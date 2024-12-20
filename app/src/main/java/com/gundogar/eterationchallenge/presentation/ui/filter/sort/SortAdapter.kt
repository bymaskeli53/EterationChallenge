package com.gundogar.eterationchallenge.presentation.ui.filter.sort

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gundogar.eterationchallenge.databinding.ItemSortByBinding
import com.gundogar.eterationchallenge.presentation.ui.filter.sort.SortAdapter.SortViewHolder

class SortAdapter(private val options: List<String>) : RecyclerView.Adapter<SortViewHolder>() {

    inner class SortViewHolder(private val binding: ItemSortByBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(option: String, position: Int) {
            binding.sortByText.text = option
            // binding.sortByRadioButton.isChecked = position == selectedPosition
//            binding.radioButton.setOnClickListener {
//                val oldPosition = selectedPosition
//                selectedPosition = position
//                notifyItemChanged(oldPosition)
//                notifyItemChanged(selectedPosition)
//                onOptionSelected(position)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortViewHolder {
        val binding = ItemSortByBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SortViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SortViewHolder, position: Int) {
        val option = options[position]
        holder.bind(option, position)
    }

    override fun getItemCount(): Int {
        return options.size
    }
}
