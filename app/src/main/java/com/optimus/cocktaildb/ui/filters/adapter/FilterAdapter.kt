package com.optimus.cocktaildb.ui.filters.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.cocktaildb.data.model.FilterItem
import com.optimus.cocktaildb.ui.filters.viewholder.FilterViewHolder

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */
class FilterAdapter(private val onItemClick: (Int) -> Unit): RecyclerView.Adapter<FilterViewHolder>() {

    private val filterItems: MutableList<FilterItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilterViewHolder.create(parent, onItemClick)

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bind(filterItems[position])
    }

    override fun getItemCount() = filterItems.size

    fun updateData(items: List<FilterItem>){
        filterItems.clear()
        filterItems.addAll(items)
        notifyDataSetChanged()
    }
}