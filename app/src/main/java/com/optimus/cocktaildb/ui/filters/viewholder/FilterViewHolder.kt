package com.optimus.cocktaildb.ui.filters.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.cocktaildb.data.model.FilterItem
import com.optimus.cocktaildb.databinding.FilterRvCellBinding

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */
class FilterViewHolder(
    private val binding: FilterRvCellBinding,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, onItemClick: (Int) -> Unit): FilterViewHolder {
            val binding =
                FilterRvCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FilterViewHolder(binding, onItemClick)
        }
    }

    fun bind(filterItem: FilterItem) {
        binding.tvFilterName.text = filterItem.drinkGroupName
        binding.checkIndicator.visibility = if (filterItem.isChecked) View.VISIBLE else View.GONE
        binding.root.setOnClickListener { onItemClick.invoke(filterItem.ordinal) }
    }
}