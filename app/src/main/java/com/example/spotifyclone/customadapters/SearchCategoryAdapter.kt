package com.example.spotifyclone.customadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spotifyclone.R
import com.example.spotifyclone.databinding.ItemSearchCategoriesBinding
import com.example.spotifyclone.models.SearchCategoryModel

class SearchCategoryAdapter :
    ListAdapter<SearchCategoryModel, SearchCategoryAdapter.ViewHolder>(SearchDiffUtil()) {

    class ViewHolder(val binding: ItemSearchCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind( categoryData: SearchCategoryModel) {
            binding.categoryTitle.text = categoryData.categoryName
            Glide.with(binding.root.context).load(categoryData.image).placeholder(R.drawable.ic_image_placeholder).into(binding.categoryImage)
        }
    }

    class SearchDiffUtil : DiffUtil.ItemCallback<SearchCategoryModel>() {
        override fun areItemsTheSame(
            oldItem: SearchCategoryModel, newItem: SearchCategoryModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SearchCategoryModel, newItem: SearchCategoryModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemSearchCategoriesBinding.inflate(LayoutInflater.from(parent.context) , parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}