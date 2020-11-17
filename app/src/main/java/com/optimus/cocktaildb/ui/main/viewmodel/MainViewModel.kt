package com.optimus.cocktaildb.ui.main.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.optimus.cocktaildb.data.model.Drink
import com.optimus.cocktaildb.data.paging.DrinkDataSource
import com.optimus.cocktaildb.data.paging.DrinkDataSourceFactory
import com.optimus.cocktaildb.utils.State
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
class MainViewModel @Inject constructor() : ViewModel() {

    private var _pagedDrinkList: LiveData<PagedList<Drink>>
    val pagedDrinkList: LiveData<PagedList<Drink>>
        get() = _pagedDrinkList

    private var _drinkDataSource: MutableLiveData<DrinkDataSource>

    init {
        val drinkDataSourceFactory = DrinkDataSourceFactory(viewModelScope)
        val config = PagedList.Config.Builder()
            .setPageSize(1)
            .setEnablePlaceholders(false)
            .build()
        _pagedDrinkList = LivePagedListBuilder(drinkDataSourceFactory, config).build()
        _drinkDataSource = drinkDataSourceFactory.drinkLiveDataSource
    }

    fun getState(): LiveData<State> =
        Transformations.switchMap(_drinkDataSource, DrinkDataSource::state)

    fun retry() {
        _drinkDataSource.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return _pagedDrinkList.value?.isEmpty() ?: true
    }

}