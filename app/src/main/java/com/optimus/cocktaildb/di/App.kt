package com.optimus.cocktaildb.di

import android.app.Application

/**
 * Created by Dmitriy Chebotar on 18.09.2020.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.createDaggerComponent(this)
    }
}