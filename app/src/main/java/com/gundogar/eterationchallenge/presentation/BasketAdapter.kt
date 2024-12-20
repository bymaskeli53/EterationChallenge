package com.gundogar.eterationchallenge.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gundogar.eterationchallenge.data.model.CartItem
import com.gundogar.eterationchallenge.databinding.ItemBasketBinding

class BasketAdapter(
    private val onQuantityChanged: (String, Int) -> Unit = { _, _ -> },
    private val onItemDeleted: (String) -> Unit = {}
) : ListAdapter<CartItem, BasketAdapter.BasketViewHolder>(BasketDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

    inner class BasketViewHolder(private val binding: ItemBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cartItem: CartItem) {
            binding.tvProductTitle.text = cartItem.name
            binding.tvProductPrice.text = cartItem.price
            binding.btnQuantity.text = cartItem.quantity.toString()
            // binding.tvQuantity.text = cartItem.quantity.toString()

            binding.btnPlus.setOnClickListener {
                onQuantityChanged(cartItem.id, cartItem.quantity + 1)
            }

            binding.btnMinus.setOnClickListener {
                if (cartItem.quantity > 1) {
                    onQuantityChanged(cartItem.id, cartItem.quantity - 1)
                } else {
                    onItemDeleted(cartItem.id)
                    submitList(currentList - cartItem)
                }
            }
        }
    }
}

class BasketDiffCallback : DiffUtil.ItemCallback<CartItem>() {
    override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean =
        oldItem == newItem
}
