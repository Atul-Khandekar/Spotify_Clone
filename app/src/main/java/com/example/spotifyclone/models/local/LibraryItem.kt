package com.example.spotifyclone.models.local

data class LibraryItem(
    val id: String?,
    val image: String?,
    val name: String?,
    val type: MediaItemType,
    val owner: String?
)