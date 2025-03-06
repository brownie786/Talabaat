package com.example.talabaat_foodorderingapp.retrofit

import com.example.talabaat_foodorderingapp.dataClass.CategoryList
import com.example.talabaat_foodorderingapp.dataClass.MealsByCategoryList
import com.example.talabaat_foodorderingapp.dataClass.MealsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiInterface {

    @GET("random.php")
    fun getRandomMeal(): Call<MealsList>

    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String): Call<MealsList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") categoryName: String): Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>
}