package com.example.talabaat_foodorderingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.talabaat_foodorderingapp.dataClass.Category
import com.example.talabaat_foodorderingapp.databinding.CategoryItemsBinding

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categoryList = ArrayList<Category>()

    fun setCategoryList(categoryList: List<Category>?) {
        this.categoryList = categoryList as ArrayList<Category>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(categoryList[position].strCategoryThumb)
            .into(holder.binding.ivCategory)
        holder.binding.tvCategory.text = categoryList[position].strCategory
    }

    override fun getItemCount(): Int = categoryList.size

    class CategoryViewHolder(val binding: CategoryItemsBinding): RecyclerView.ViewHolder(binding.root) {

    }
}