package com.example.spotifyclone.customadapters

import androidx.recyclerview.widget.DiffUtil
import com.example.spotifyclone.R
import com.example.spotifyclone.base.BaseAdapter
import com.example.spotifyclone.models.local.SearchData

class SearchSongAdapter : BaseAdapter<SearchData>(SearchDiffUtil()) {

    class SearchDiffUtil: DiffUtil.ItemCallback<SearchData>() {
        override fun areItemsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SearchData, newItem: SearchData): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_song
    }

}