package com.example.talabaat_foodorderingapp.retrofit

import com.example.talabaat_foodorderingapp.dataClass.MealsList
import retrofit2.Call
import retrofit2.http.GET

interface MealApiInterface {

    @GET("random.php")
    fun getRandomMeal(): Call<MealsList>
}