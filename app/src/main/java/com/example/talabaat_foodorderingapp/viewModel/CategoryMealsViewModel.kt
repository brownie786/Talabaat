package com.example.talabaat_foodorderingapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.talabaat_foodorderingapp.dataClass.MealsByCategory
import com.example.talabaat_foodorderingapp.dataClass.MealsByCategoryList
import com.example.talabaat_foodorderingapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel: ViewModel() {

    private val mealsListByCategoryLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getMealsByCategory(categoryName: String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<MealsByCategoryList?> {
            override fun onResponse(call: Call<MealsByCategoryList?>, response: Response<MealsByCategoryList?>
            ) {
                if(response.isSuccessful){
//                    mealsListByCategoryLiveData.value = response.body()?.meals
                    Log.d("API_RESPONSE", "Response: ${response.body()}")
                    response.body()?.meals?.let {
                        mealsListByCategoryLiveData.postValue(it)
                    }
                }else{
                    Log.e("API_ERROR", "Response Code: ${response.code()}, Message: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<MealsByCategoryList?>, t: Throwable) {
                Log.d("API_FAILURE", t.message.toString())
            }
        })
    }

    fun observeMealsListByCategoryLiveData():   LiveData<List<MealsByCategory>> {
        return mealsListByCategoryLiveData
    }
}