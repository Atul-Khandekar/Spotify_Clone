package com.example.spotifyclone.models

import java.util.UUID

data class SearchSongModel(
    val id: UUID,
    val image: String,
    val name: String,
    val artistName: String
)
