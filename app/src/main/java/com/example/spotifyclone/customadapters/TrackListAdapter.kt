package com.example.spotifyclone.customadapters

import androidx.recyclerview.widget.DiffUtil
import com.example.spotifyclone.R
import com.example.spotifyclone.base.BaseAdapter
import com.example.spotifyclone.models.local.DisplaySongData

class TrackListAdapter: BaseAdapter<DisplaySongData>(TrackDiffUtil()) {
    class TrackDiffUtil: DiffUtil.ItemCallback<DisplaySongData>() {
        override fun areItemsTheSame(oldItem: DisplaySongData, newItem: DisplaySongData): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: DisplaySongData, newItem: DisplaySongData): Boolean {
           return oldItem ==  newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_track_rv
    }

}