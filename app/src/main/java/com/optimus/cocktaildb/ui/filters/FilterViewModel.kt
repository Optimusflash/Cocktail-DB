package com.optimus.cocktaildb.ui.filters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.optimus.cocktaildb.data.model.FilterItem
import com.optimus.cocktaildb.data.repositories.FilterRepository
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 17.11.2020.
 */
class FilterViewModel @Inject constructor(private val repository: FilterRepository): ViewModel() {

    private var _filterItems: MutableLiveData<List<FilterItem>> = MutableLiveData()
    val filterItems: LiveData<List<FilterItem>>
        get() = _filterItems

    init {
        _filterItems.value = repository.getDrinksFilterItems()
    }

    fun onFilterItemClick(id: Int){
        Log.e("M_FilterViewModel", "$id")
        val filterItem = repository.findFilterItem(id) ?: return
        filterItem.isChecked = filterItem.isChecked.not()
        repository.updateFilterItem(filterItem)
        _filterItems.value = repository.getDrinksFilterItems()
    }
}