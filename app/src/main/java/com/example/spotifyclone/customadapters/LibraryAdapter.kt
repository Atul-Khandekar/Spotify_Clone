package com.example.spotifyclone.customadapters

import androidx.recyclerview.widget.DiffUtil
import com.example.spotifyclone.R
import com.example.spotifyclone.base.BaseAdapter
import com.example.spotifyclone.models.local.LibraryItem

class LibraryAdapter: BaseAdapter<LibraryItem>(LibraryDiffUtil()) {

    class LibraryDiffUtil: DiffUtil.ItemCallback<LibraryItem>() {
        override fun areItemsTheSame(oldItem: LibraryItem, newItem: LibraryItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LibraryItem, newItem: LibraryItem): Boolean {
           return oldItem == newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_your_library
    }

}