package com.example.spotifyclone.bindings

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.spotifyclone.models.local.MediaItemType

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url: String?) {
    url?.let {
        Glide.with(context).load(it).into(this)
    }
}

@BindingAdapter("setType")
fun TextView.setType(type: MediaItemType) {
    this.text = when(type) {
        is MediaItemType.Album -> {
            MediaItemType.Album().value
        }
        is MediaItemType.Playlist -> {
            MediaItemType.Playlist().value
        }
        is MediaItemType.Track -> {
            MediaItemType.Track().value
        }
    }
}