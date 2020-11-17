package com.optimus.cocktaildb.di.modules

import androidx.lifecycle.ViewModel
import com.optimus.cocktaildb.di.ViewModelKey
import com.optimus.cocktaildb.ui.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Dmitriy Chebotar on 18.09.2020.
 */
@Module
abstract class ViewModelModule {
    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    abstract fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

//    @IntoMap
//    @Binds
//    @ViewModelKey(DetailsViewModel::class)
//    abstract fun provideDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel
}