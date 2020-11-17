package com.optimus.cocktaildb.data.model

import com.google.gson.annotations.SerializedName
import com.optimus.cocktaildb.utils.DrinkCategory

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
data class Drink (
    @SerializedName("idDrink") val id: Int? = null,
    @SerializedName("strDrink") var name: String? = null,
    @SerializedName("strDrinkThumb") val imageUrl: String? = null,
    val viewType: ViewType = ViewType.ITEM,
)