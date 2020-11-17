package com.optimus.cocktaildb.ui.main.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.optimus.cocktaildb.databinding.ActivityMainBinding
import com.optimus.cocktaildb.di.Injector
import com.optimus.cocktaildb.di.ViewModelFactory
import com.optimus.cocktaildb.ui.main.viewmodel.MainViewModel
import com.optimus.cocktaildb.ui.main.adapters.DrinkPagingAdapter
import com.optimus.cocktaildb.utils.State
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    private val drinkPagingAdapter by lazy {
        DrinkPagingAdapter( mainViewModel::retry)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

    }

    private fun initViews() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = drinkPagingAdapter
    }

    private fun setObservers() {
        mainViewModel.pagedDrinkList.observe(this, {
            drinkPagingAdapter.submitList(it)
        })

        mainViewModel.getState().observe(this, {
            if (mainViewModel.listIsEmpty().not()) {
                drinkPagingAdapter.setState(it ?: State.DONE)
            }
        })
    }
}