package com.optimus.cocktaildb.di


import com.optimus.cocktaildb.data.paging.DrinkDataSourceFactory
import com.optimus.cocktaildb.di.modules.RemoteModule
import com.optimus.cocktaildb.di.modules.ViewModelModule
import com.optimus.cocktaildb.ui.filters.FilterActivity
import com.optimus.cocktaildb.ui.main.activities.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Dmitriy Chebotar on 18.09.2020.
 */
@Singleton
@Component(modules = [RemoteModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(filterActivity: FilterActivity)
    fun inject(drinkDataSourceFactory: DrinkDataSourceFactory)
}