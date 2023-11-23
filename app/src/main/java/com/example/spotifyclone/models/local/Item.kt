package com.example.spotifyclone.models.local


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("added_at")
    val addedAt: String?,
    @SerializedName("added_by")
    val addedBy: AddedBy?,
    @SerializedName("is_local")
    val isLocal: Boolean?,
    @SerializedName("track")
    val track: Track?
)