package com.example.bobopizza.domain.api

import com.example.bobopizza.domain.models.MenuModel
import com.example.bobopizza.domain.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers

interface PizzaApi {
    @Headers(
        "X-RapidAPI-Host: pizza-and-desserts.p.rapidapi.com",
        "X-RapidAPI-Key: $API_KEY"
    )

    @GET("pizzas/")
    suspend fun getMenuModel(): MenuModel
}