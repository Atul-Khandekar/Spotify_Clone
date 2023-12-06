package com.example.spotifyclone.customadapters

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.spotifyclone.R
import com.example.spotifyclone.base.BaseAdapter
import com.example.spotifyclone.databinding.ItemSearchCategoriesBinding
import com.example.spotifyclone.extensions.randomColor
import com.example.spotifyclone.models.remote.CategoryItem

class SearchCategoryAdapter: BaseAdapter<CategoryItem>(SearchDiffUtil()) {

    class SearchDiffUtil: DiffUtil.ItemCallback<CategoryItem>() {
        override fun areItemsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CategoryItem, newItem: CategoryItem): Boolean {
            return oldItem ==  newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_search_categories
    }

    override fun setDataForItems(binding: ViewDataBinding, item: CategoryItem) {
        super.setDataForItems(binding, item)
        (binding as ItemSearchCategoriesBinding).apply {
            categoryConstraintLayout.setBackgroundColor(Int.randomColor())
        }
    }
}

