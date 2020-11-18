package com.optimus.cocktaildb.ui.main.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.optimus.cocktaildb.data.model.Drink
import com.optimus.cocktaildb.databinding.DrinkRvCellBinding
import com.optimus.cocktaildb.extensions.loadImage

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */

class DrinkItemViewHolder(private val binding: DrinkRvCellBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): DrinkItemViewHolder {
            val binding =
                    DrinkRvCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return DrinkItemViewHolder(binding)
        }
    }

    fun bind(drink: Drink) {
        binding.tvDrinkName.text = drink.name
        binding.ivDrink.loadImage(drink.imageUrl)
    }
}