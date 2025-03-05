package com.example.talabaat_foodorderingapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.talabaat_foodorderingapp.R
import com.example.talabaat_foodorderingapp.dataClass.Meal
import com.example.talabaat_foodorderingapp.databinding.ActivityMealBinding
import com.example.talabaat_foodorderingapp.fragments.HomeFragment
import com.example.talabaat_foodorderingapp.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var mealMvvm: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]
        getMealInformation()
        setInformationInViews()
        mealMvvm.getMealDetails(mealId)
        observerMealDetailsLiveData()
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformation() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID).toString()
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME).toString()
        mealThumb = intent.getStringExtra(HomeFragment.Meal_THUMB).toString()
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observeMealDetailsLiveData().observe(this, object: Observer<Meal>{
            override fun onChanged(value: Meal) {
                 val meal = value

                val category = "Category: ${meal.strCategory}"
                val area = "Area: ${meal.strArea}"
                val instructions = "Instructions: ${meal.strInstructions}"
            }

        })
    }


}