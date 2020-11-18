package com.optimus.cocktaildb.ui.main.viewholders

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.cocktaildb.databinding.DrinkFooterSellBinding
import com.optimus.cocktaildb.utils.State

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */

class DrinkFooterViewHolder(
    private val binding: DrinkFooterSellBinding,
    private val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, onPostClick: () -> Unit): DrinkFooterViewHolder {
            val binding = DrinkFooterSellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DrinkFooterViewHolder(binding, onPostClick)
        }
    }

    fun bind (state: State){
        Log.e("M_DrinkFooterViewHolder", "$state")
        binding.progressBar.visibility = if (state == State.LOADING) View.VISIBLE else View.GONE
        binding.btnRetry.visibility = if (state == State.ERROR) View.VISIBLE else View.GONE
        binding.tvError.visibility = if (state == State.ERROR) View.VISIBLE else View.GONE
        binding.btnRetry.setOnClickListener { retryCallback.invoke()  }
    }
}