package com.optimus.cocktaildb.utils

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
enum class DrinkCategory(val drinkGroupName: String){
    COCKTAIL("Cocktail"),
    MILK_FLOAT_SHAKE("Milk / Float / Shake"),
    OTHER_UNKNOWN("Other/Unknown"),
    COCOA("Cocoa"),
    SHOT("Shot"),
    COFFEE_TEA("Coffee / Tea"),
    HOMEMADE_LIQUEUR("Homemade Liqueur"),
    BEER("Beer"),
    PUNCH_PARTY_DRINK("Punch / Party Drink");

    fun nextCategory(): String? {
        return if (this.ordinal< values().lastIndex){
            values()[this.ordinal +1].drinkGroupName
        } else{
            null
        }
    }
}