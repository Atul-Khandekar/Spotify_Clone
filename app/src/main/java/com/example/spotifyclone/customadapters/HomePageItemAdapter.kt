package com.example.spotifyclone.customadapters

import androidx.recyclerview.widget.DiffUtil
import com.example.spotifyclone.R
import com.example.spotifyclone.base.BaseAdapter
import com.example.spotifyclone.models.local.HomePageItem

class HomePageItemAdapter : BaseAdapter<HomePageItem>(ItemDiffUtil()) {

    class ItemDiffUtil : DiffUtil.ItemCallback<HomePageItem>() {
        override fun areItemsTheSame(oldItem: HomePageItem, newItem: HomePageItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HomePageItem, newItem: HomePageItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_horizontal_recycler_view
    }
}