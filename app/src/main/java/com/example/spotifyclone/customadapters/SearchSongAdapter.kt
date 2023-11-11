package com.example.spotifyclone.customadapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyclone.databinding.ItemSongBinding
import com.example.spotifyclone.models.local.SearchSongModel

class SearchSongAdapter : ListAdapter<SearchSongModel, SearchSongAdapter.ViewHolder>(SearchSongDiffUtil()) {

    class ViewHolder(private val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchSongData: SearchSongModel) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class SearchSongDiffUtil: DiffUtil.ItemCallback<SearchSongModel>() {
        override fun areItemsTheSame(oldItem: SearchSongModel, newItem: SearchSongModel): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(
            oldItem: SearchSongModel,
            newItem: SearchSongModel
        ): Boolean {
            TODO("Not yet implemented")
        }

    }

}