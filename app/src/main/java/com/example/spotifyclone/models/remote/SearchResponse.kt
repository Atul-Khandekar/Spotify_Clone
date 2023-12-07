package com.example.spotifyclone.models.remote

import com.example.spotifyclone.models.local.Track
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("tracks") val tracks: ResponseItem<Track>?,
    @SerializedName("albums") val albums: ResponseItem<AlbumItem>?,
    @SerializedName("playlists") val playlists: ResponseItem<PlaylistItem>?
)