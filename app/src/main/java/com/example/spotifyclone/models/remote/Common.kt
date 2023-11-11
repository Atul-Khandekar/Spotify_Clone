package com.example.spotifyclone.models.remote

import com.google.gson.annotations.SerializedName

data class ExternalUrls(
    @SerializedName("spotify")
    val spotify: String?
)

data class Image(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
)