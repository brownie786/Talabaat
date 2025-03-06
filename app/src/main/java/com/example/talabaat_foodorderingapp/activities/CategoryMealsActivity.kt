package com.example.talabaat_foodorderingapp.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.talabaat_foodorderingapp.R
import com.example.talabaat_foodorderingapp.adapter.CategoryMealsAdapter
import com.example.talabaat_foodorderingapp.databinding.ActivityCategoryMealsBinding
import com.example.talabaat_foodorderingapp.fragments.HomeFragment
import com.example.talabaat_foodorderingapp.viewModel.CategoryMealsViewModel
import com.example.talabaat_foodorderingapp.viewModel.MealViewModel

class CategoryMealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryMealsBinding
    private lateinit var categoryName: String
    private lateinit var categoryMealsViewModel: CategoryMealsViewModel
    private lateinit var categoryMealsAdapter: CategoryMealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        categoryMealsViewModel = ViewModelProvider(this)[CategoryMealsViewModel::class.java]
        categoryName = intent.getStringExtra(HomeFragment.CATEGORY_NAME)!!.toString()

        categoryMealsViewModel.getMealsByCategory(categoryName)

        observerMealsListLiveData()
    }

    private fun prepareRecyclerView() {
        categoryMealsAdapter = CategoryMealsAdapter()
        binding.rvCategoryMeals.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.rvCategoryMeals.adapter = categoryMealsAdapter
    }

    private fun observerMealsListLiveData() {
        categoryMealsViewModel.observeMealsListByCategoryLiveData().observe(this, Observer {
            mealList ->
            binding.tvCategoryCount.text = mealList.size.toString()
            categoryMealsAdapter.setMealsList(mealList)
//            mealList.forEach {
//                Log.d("TEST", it.strMeal)
//            }
        })
    }
}