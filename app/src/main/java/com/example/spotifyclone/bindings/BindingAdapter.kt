package com.example.spotifyclone.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url: String) {
    Glide.with(context).load(url).into(this)
}