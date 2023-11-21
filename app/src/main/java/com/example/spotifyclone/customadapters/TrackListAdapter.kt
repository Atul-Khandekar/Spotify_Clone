package com.example.spotifyclone.customadapters

import androidx.recyclerview.widget.DiffUtil
import com.example.spotifyclone.R
import com.example.spotifyclone.base.BaseAdapter
import com.example.spotifyclone.models.local.TrackListItem

class TrackListAdapter: BaseAdapter<TrackListItem>(TrackDiffUtil()) {
    class TrackDiffUtil: DiffUtil.ItemCallback<TrackListItem>() {
        override fun areItemsTheSame(oldItem: TrackListItem, newItem: TrackListItem): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(oldItem: TrackListItem, newItem: TrackListItem): Boolean {
           return oldItem ==  newItem
        }

    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_track_rv
    }

}