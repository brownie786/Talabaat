package com.example.talabaat_foodorderingapp.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
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
    private lateinit var mealYoutubeLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealMvvm = ViewModelProvider(this)[MealViewModel::class.java]
        getMealInformation()
        setInformationInViews()
        loadingCase()
        mealMvvm.getMealDetails(mealId)
        observerMealDetailsLiveData()
        onYoutubeImgClick()
    }

    private fun onYoutubeImgClick() {
        binding.imgYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mealYoutubeLink))
            startActivity(intent)
        }
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
                onResponseCase()

                binding.tvCategory.text = "Category: ${value.strCategory}"
                binding.tvArea.text = "Area: ${value.strArea}"
                binding.tvInstruction.text = "Instructions: ${value.strInstructions}"

                mealYoutubeLink = value.strYoutube
            }
        })
    }

    private fun loadingCase(){
        binding.progressBar.visibility = View.VISIBLE
        binding.btnSave.visibility = View.GONE
        binding.tvCategory.visibility = View.GONE
        binding.tvArea.visibility = View.GONE
        binding.tvInstruction.visibility = View.GONE
    }

    private fun onResponseCase(){
        binding.progressBar.visibility = View.GONE
        binding.btnSave.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.tvInstruction.visibility = View.VISIBLE
    }
}