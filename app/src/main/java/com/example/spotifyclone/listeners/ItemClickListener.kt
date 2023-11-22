package com.example.spotifyclone.listeners

interface ItemClickListener <T> {
    fun onItemClick(item: T, position: Int)
}