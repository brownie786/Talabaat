package com.example.talabaat_foodorderingapp.viewModel

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.talabaat_foodorderingapp.R
import com.example.talabaat_foodorderingapp.dataClass.Meal
import com.example.talabaat_foodorderingapp.dataClass.MealsList
import com.example.talabaat_foodorderingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel(): ViewModel(){

    private var mealDetailsLiveData= MutableLiveData<Meal>()

    fun getMealDetails(id: String){
        RetrofitInstance.api.getMealDetails(id).enqueue(object : Callback<MealsList?> {
            override fun onResponse(call: Call<MealsList?>, response: Response<MealsList?>) {
                if(response.body()!= null){
                    mealDetailsLiveData.value = response.body()!!.meals[0]
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealsList?>, t: Throwable) {
                Log.d("MealVM", t.message.toString())
            }
        })
    }

    fun observeMealDetailsLiveData(): LiveData<Meal>{
        return mealDetailsLiveData
    }
}