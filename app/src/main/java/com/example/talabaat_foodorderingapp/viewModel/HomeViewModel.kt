package com.example.talabaat_foodorderingapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.talabaat_foodorderingapp.dataClass.CategoryList
import com.example.talabaat_foodorderingapp.dataClass.CategoryMeals
import com.example.talabaat_foodorderingapp.dataClass.Meal
import com.example.talabaat_foodorderingapp.dataClass.MealsList
import com.example.talabaat_foodorderingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {
    private var randomMealLiveData= MutableLiveData<Meal>()
    private var popularItemsLiveData= MutableLiveData<List<CategoryMeals>>()

    fun getRandomMeal() {
        RetrofitInstance.api.getRandomMeal().enqueue(object : Callback<MealsList?> {
            override fun onResponse(call: Call<MealsList?>, response: Response<MealsList?>) {
                if(response.body()!= null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                }else{
                    return
                }
            }
            override fun onFailure(call: Call<MealsList?>, t: Throwable) {
                Log.d("HomeVM", t.message.toString())
            }
        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : Callback<CategoryList?> {
            override fun onResponse(call: Call<CategoryList?>, response: Response<CategoryList?>) {
                if(response.body()!= null){
                    popularItemsLiveData.value = response.body()!!.meals
                }else
                    return
            }

            override fun onFailure(call: Call<CategoryList?>, t: Throwable) {
                Log.d("HomeVM", t.message.toString())
            }
        })
    }

    fun observeRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }

}