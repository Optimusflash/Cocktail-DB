package com.optimus.cocktaildb.ui.filters

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.optimus.cocktaildb.R
import com.optimus.cocktaildb.databinding.ActivityFiltersBinding
import com.optimus.cocktaildb.databinding.ActivityMainBinding
import com.optimus.cocktaildb.di.Injector
import com.optimus.cocktaildb.di.ViewModelFactory
import com.optimus.cocktaildb.ui.main.viewmodel.MainViewModel
import javax.inject.Inject

class FilterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFiltersBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var filterViewModel: FilterViewModel

    companion object {
        fun newIntent(context: Context) = Intent(context, FilterActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initDaggerComponent()
        initViewModels()
        initViews()
        setObservers()
    }

    private fun initDaggerComponent() {
        Injector.getAppComponent().inject(this)
    }

    private fun initViewModels() {
        filterViewModel = ViewModelProvider(this, viewModelFactory).get(FilterViewModel::class.java)
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Filters"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setObservers() {

    }
}