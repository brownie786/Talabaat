package com.example.talabaat_foodorderingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.talabaat_foodorderingapp.dataClass.CategoryList
import com.example.talabaat_foodorderingapp.dataClass.CategoryMeals
import com.example.talabaat_foodorderingapp.databinding.PopularItemsBinding

class PopularAdapter(): RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

    lateinit var onItemClick: ((CategoryMeals) -> Unit)
    private var mealsList = ArrayList<CategoryMeals>()

    fun setMealsList(mealsList: ArrayList<CategoryMeals>){
        this.mealsList = mealsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(mealsList[position].strMealThumb)
            .into(holder.binding.imgPopular)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(mealsList[position])
        }
    }

    override fun getItemCount(): Int = mealsList.size

    class PopularViewHolder(val binding: PopularItemsBinding):
        RecyclerView.ViewHolder(binding.root){

    }
}