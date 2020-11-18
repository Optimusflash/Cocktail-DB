package com.optimus.cocktaildb.data.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.optimus.cocktaildb.data.model.Drink
import com.optimus.cocktaildb.data.model.FilterItem
import com.optimus.cocktaildb.data.model.ViewType
import com.optimus.cocktaildb.data.remote.DrinkApiService
import com.optimus.cocktaildb.utils.State
import kotlinx.coroutines.*

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
class DrinkDataSource(
        filters: List<FilterItem>,
        private val scope: CoroutineScope,
        private val api: DrinkApiService
) : PageKeyedDataSource<String, Drink>() {

    val state: MutableLiveData<State> = MutableLiveData()
    private lateinit var retryBlock: () -> Unit
    private val mutableFiltersList = filters.toMutableList()

    override fun loadInitial(
            params: LoadInitialParams<String>,
            callback: LoadInitialCallback<String, Drink>
    ) {
//        if (mutableFiltersList.isEmpty()) return
        scope.launch {
            try {
                updateState(State.LOADING)
                val result = withContext(Dispatchers.IO) {
                    api.loadDrink(mutableFiltersList.first().drinkGroupName).drinks
                }
                val mutableList = result.toMutableList()
                mutableList.add(0, Drink(viewType = ViewType.HEADER, name = mutableFiltersList.first().drinkGroupName))
                callback.onResult(mutableList, null, nextKey(mutableFiltersList.first().drinkGroupName))
                updateState(State.DONE)
            } catch (e: Exception) {
                Log.e("M_DrinkDataSource", "sdfsdfsdf")
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
                val result = withContext(Dispatchers.IO) {
                    api.loadDrink(key).drinks
                }
                val mutableList = result.toMutableList()
                mutableList.add(0, Drink(viewType = ViewType.HEADER, name = key))
                callback.onResult(mutableList, nextKey(key))
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
        val index = mutableFiltersList.indexOfFirst { it.drinkGroupName == previousKey }
        if (index == mutableFiltersList.lastIndex) return null
        return mutableFiltersList[index+1].drinkGroupName
    }

    fun retry() {
        retryBlock.invoke()
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }
}