package com.optimus.cocktaildb.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.optimus.cocktaildb.data.model.Drink
import com.optimus.cocktaildb.data.remote.DrinkApiService
import com.optimus.cocktaildb.di.Injector
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
class DrinkDataSourceFactory(private val scope: CoroutineScope) :
    DataSource.Factory<String, Drink>() {

    private val _drinkLiveDataSource = MutableLiveData<DrinkDataSource>()
    val drinkLiveDataSource: MutableLiveData<DrinkDataSource>
        get() = _drinkLiveDataSource

    @Inject
    lateinit var api: DrinkApiService

    init {
        Injector.getAppComponent().inject(this)
    }

    override fun create(): DataSource<String, Drink> {
        val drinkDataSource = DrinkDataSource(scope, api)
        _drinkLiveDataSource.postValue(drinkDataSource)
        return drinkDataSource
    }
}