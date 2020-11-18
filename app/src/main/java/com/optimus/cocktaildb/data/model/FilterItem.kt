package com.optimus.cocktaildb.data.model

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
enum class FilterItem(val drinkGroupName: String, var isChecked: Boolean = true){
    COCKTAIL("Cocktail"),
    MILK_FLOAT_SHAKE("Milk / Float / Shake"),
    OTHER_UNKNOWN("Other/Unknown"),
    COCOA("Cocoa"),
    SHOT("Shot"),
    COFFEE_TEA("Coffee / Tea"),
    HOMEMADE_LIQUEUR("Homemade Liqueur"),
    BEER("Beer"),
    PUNCH_PARTY_DRINK("Punch / Party Drink");
}