package com.optimus.cocktaildb.ui.main.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.cocktaildb.data.model.Drink
import com.optimus.cocktaildb.databinding.DrinkHeaderSellBinding

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */
class DrinkHeaderViewHolder( private val binding: DrinkHeaderSellBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): DrinkHeaderViewHolder {
            val binding = DrinkHeaderSellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DrinkHeaderViewHolder(binding)
        }
    }

    fun bind (drink: Drink){
        binding.tvGroupName.text = drink.name
    }
}