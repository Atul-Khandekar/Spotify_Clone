package com.example.spotifyclone.models.local

data class SearchData(
    val id: String,
    val image: String?,
    val title: String,
    val type: MediaItemType,
    val artists: String,
)