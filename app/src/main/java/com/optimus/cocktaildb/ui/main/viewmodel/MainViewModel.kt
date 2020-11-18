package com.optimus.cocktaildb.ui.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.optimus.cocktaildb.data.model.Drink
import com.optimus.cocktaildb.data.model.FilterItem
import com.optimus.cocktaildb.data.paging.DrinkDataSource
import com.optimus.cocktaildb.data.paging.DrinkDataSourceFactory
import com.optimus.cocktaildb.data.repositories.FilterRepository
import com.optimus.cocktaildb.utils.SingleLiveEvent
import com.optimus.cocktaildb.utils.State
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
class MainViewModel @Inject constructor(private val repository: FilterRepository) : ViewModel() {

    private var _pagedDrinkList: LiveData<PagedList<Drink>>
    val pagedDrinkList: LiveData<PagedList<Drink>>
        get() = _pagedDrinkList

    private var _drinkDataSource: MutableLiveData<DrinkDataSource>

    private val drinkDataSourceFactory = DrinkDataSourceFactory(viewModelScope)

    val filterItems: SingleLiveEvent<List<FilterItem>>
        get() = repository.filterItems

    init {
        val config = PagedList.Config.Builder()
                .setPageSize(10)
                .setEnablePlaceholders(false)
                .build()
        _pagedDrinkList = LivePagedListBuilder(drinkDataSourceFactory, config).build()
        _drinkDataSource = drinkDataSourceFactory.drinkLiveDataSource
        filterItems.value?.let { makeRequest(it) }
    }

    fun getState(): LiveData<State> =
            Transformations.switchMap(_drinkDataSource, DrinkDataSource::state)

    fun retry() {
        _drinkDataSource.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return _pagedDrinkList.value?.isEmpty() ?: true
    }

    fun makeRequest(it: List<FilterItem>) {
        val result = it.filter { it.isChecked }
        drinkDataSourceFactory.setFilterItems(result)
        _drinkDataSource.value?.invalidate()
    }
}