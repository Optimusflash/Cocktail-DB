package com.optimus.cocktaildb.ui.main.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.optimus.cocktaildb.data.model.Drink
import com.optimus.cocktaildb.data.model.ViewType
import com.optimus.cocktaildb.ui.main.viewholders.DrinkFooterViewHolder
import com.optimus.cocktaildb.ui.main.viewholders.DrinkHeaderViewHolder
import com.optimus.cocktaildb.ui.main.viewholders.DrinkItemViewHolder
import com.optimus.cocktaildb.utils.State

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */


class DrinkPagingAdapter(private val onRetryClick: ()->Unit) : PagedListAdapter<Drink, RecyclerView.ViewHolder>(
    DIFF_COMPARATOR
) {
    private var state = State.LOADING
    companion object {
        private val DIFF_COMPARATOR = object : DiffUtil.ItemCallback<Drink>() {
            override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) {
            super.getItem(position)!!.viewType.ordinal  //TODO fix
        } else {
            ViewType.FOOTER.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.ITEM.ordinal -> {
                DrinkItemViewHolder.create(parent)
            }
            ViewType.HEADER.ordinal -> {
                DrinkHeaderViewHolder.create(parent)
            }
            else -> {
                DrinkFooterViewHolder.create(parent,onRetryClick)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ViewType.ITEM.ordinal -> {
                getItem(position)?.let {
                    (holder as DrinkItemViewHolder).bind(it)
                }
            }
            ViewType.HEADER.ordinal ->{
                getItem(position)?.let {
                    (holder as DrinkHeaderViewHolder).bind(it)
                }
            }
            ViewType.FOOTER.ordinal -> {
                    (holder as DrinkFooterViewHolder).bind(state)
            }
        }
    }
    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}