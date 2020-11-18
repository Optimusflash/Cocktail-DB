package com.optimus.cocktaildb.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.optimus.cocktaildb.data.model.FilterItem
import com.optimus.cocktaildb.utils.SingleLiveEvent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */

@Singleton
class FilterRepository  @Inject constructor() {

    private val drinkCategories = FilterItem.values().toMutableList()

    private val _filterItems: SingleLiveEvent<List<FilterItem>> = SingleLiveEvent()
    val filterItems: SingleLiveEvent<List<FilterItem>>
        get() = _filterItems

    init {
        _filterItems.value = drinkCategories
    }

    fun findFilterItem(id: Int): FilterItem? {
        val index = drinkCategories.indexOfFirst { it.ordinal == id }
        return drinkCategories[index]
    }

    fun updateFilterItem(filterItem: FilterItem) {
        val index = drinkCategories.indexOfFirst { it == filterItem }
        drinkCategories[index] = filterItem
        _filterItems.value = drinkCategories
    }

    fun getDrinksFilterItems() = drinkCategories
}