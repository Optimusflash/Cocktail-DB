package com.optimus.cocktaildb.data.remote

import com.optimus.cocktaildb.data.model.DrinkResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

/**
 * Created by Dmitriy Chebotar on 16.11.2020.
 */
interface DrinkApiService {
    @GET("api/json/v1/1/filter.php")
    suspend fun loadDrink(
        @Query(encoded = true, value = "c") category: String?
    ): DrinkResponse
}