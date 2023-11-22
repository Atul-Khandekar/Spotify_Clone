package com.example.spotifyclone.customadapters

import androidx.recyclerview.widget.DiffUtil
import com.example.spotifyclone.R
import com.example.spotifyclone.base.BaseAdapter
import com.example.spotifyclone.models.local.HomePageItemData

class HomePageItemAdapter : BaseAdapter<HomePageItemData>(ItemDiffUtil()) {

    class ItemDiffUtil : DiffUtil.ItemCallback<HomePageItemData>() {
        override fun areItemsTheSame(oldItem: HomePageItemData, newItem: HomePageItemData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomePageItemData, newItem: HomePageItemData): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_horizontal_recycler_view
    }
}