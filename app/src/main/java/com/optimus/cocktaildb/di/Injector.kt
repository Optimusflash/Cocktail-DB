package com.optimus.cocktaildb.di

import android.app.Application


/**
 * Created by Dmitriy Chebotar on 18.09.2020.
 */

object Injector {
        private lateinit var appComponent: AppComponent

        fun createDaggerComponent(app: Application){
            appComponent = DaggerAppComponent.create()
        }

        fun getAppComponent() = appComponent
}