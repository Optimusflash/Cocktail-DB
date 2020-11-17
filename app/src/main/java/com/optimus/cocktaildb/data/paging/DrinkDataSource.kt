package com.optimus.cocktaildb.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.optimus.cocktaildb.data.model.Drink
import com.optimus.cocktaildb.data.model.ViewType
import com.optimus.cocktaildb.data.remote.DrinkApiService
import com.optimus.cocktaildb.utils.DrinkCategory
import com.optimus.cocktaildb.utils.State
import kotlinx.coroutines.*

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
class DrinkDataSource(
    private val scope: CoroutineScope,
    private val api: DrinkApiService
) : PageKeyedDataSource<String, Drink>() {

    val state: MutableLiveData<State> = MutableLiveData()
    private lateinit var retryBlock: () -> Unit
    private val listCategory = DrinkCategory.values().toMutableList()

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Drink>
    ) {
        scope.launch {
            try {
                updateState(State.LOADING)
                val result = withContext(Dispatchers.IO) {
                    api.loadDrink(DrinkCategory.COCKTAIL.drinkGroupName).drinks
                }
                val mutableList = result.toMutableList()
                mutableList.add(
                    0,
                    Drink(viewType = ViewType.HEADER, name = DrinkCategory.COCKTAIL.drinkGroupName)
                )
                callback.onResult(mutableList, null, DrinkCategory.MILK_FLOAT_SHAKE.drinkGroupName)
                updateState(State.DONE)
            } catch (e: Exception) {
                updateState(State.ERROR)
                retryBlock = { loadInitial(params, callback) }
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, Drink>
    ) {
        scope.launch {
            try {
                updateState(State.LOADING)
                val key = params.key
                val nextKey = nextKey(key)
                if (nextKey == null) {
                    updateState(State.DONE)
                    this.coroutineContext.cancel()
                    return@launch
                }
                val result = withContext(Dispatchers.IO) {
                    api.loadDrink(nextKey).drinks
                }
                val toMutableList = result.toMutableList()
                toMutableList.add(0, Drink(viewType = ViewType.HEADER, name = nextKey))
                callback.onResult(toMutableList, nextKey)
                updateState(State.DONE)
            } catch (e: Exception) {
                updateState(State.ERROR)
                retryBlock = { loadAfter(params, callback) }
            }
        }
    }


    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Drink>) {

    }

    private fun nextKey(previousKey: String): String? {
        val drinkCategory = listCategory.firstOrNull { it.drinkGroupName == previousKey }
        return drinkCategory?.nextCategory()
    }

    fun retry() {
        retryBlock.invoke()
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }
}