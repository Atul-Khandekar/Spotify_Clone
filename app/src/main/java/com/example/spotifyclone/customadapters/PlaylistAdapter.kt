package com.example.spotifyclone.customadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spotifyclone.R
import com.example.spotifyclone.databinding.ItemYourLibraryBinding
import com.example.spotifyclone.models.PlayListModel

class PlaylistAdapter : ListAdapter<PlayListModel, PlaylistAdapter.ViewHolder>(PlayListDiffUtil()) {

    class ViewHolder(private val binding: ItemYourLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(playListData: PlayListModel) {
            Glide.with(binding.root.context).load(playListData.image)
                .placeholder(R.drawable.ic_playlist_background).into(binding.playlistImageView)
            binding.playlistName.text = playListData.playListName
            binding.songsCount.text = playListData.songCount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemYourLibraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class PlayListDiffUtil : DiffUtil.ItemCallback<PlayListModel>() {
        override fun areItemsTheSame(oldItem: PlayListModel, newItem: PlayListModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlayListModel, newItem: PlayListModel): Boolean {
            return oldItem == newItem
        }

    }
}