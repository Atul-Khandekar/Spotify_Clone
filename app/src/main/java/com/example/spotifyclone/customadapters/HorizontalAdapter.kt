package com.example.spotifyclone.customadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spotifyclone.models.AlbumModel
import com.example.spotifyclone.databinding.ItemHorizontalRecyclerViewBinding

class HorizontalAdapter : ListAdapter<AlbumModel, HorizontalAdapter.ViewHolder>(DiffUtil()) {


    class ViewHolder(val binding: ItemHorizontalRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: AlbumModel) {
            binding.textArtists.text = data.names
            Glide.with(binding.root.context).load(data.image).into(binding.coverImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHorizontalRecyclerViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<AlbumModel>() {
        override fun areItemsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlbumModel, newItem: AlbumModel): Boolean {
            return oldItem == newItem
        }

    }

}
