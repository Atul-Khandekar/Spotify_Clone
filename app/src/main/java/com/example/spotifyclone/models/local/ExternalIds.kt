package com.example.spotifyclone.models.local


import com.google.gson.annotations.SerializedName

data class ExternalIds(
    @SerializedName("ean")
    val ean: String?,
    @SerializedName("isrc")
    val isrc: String?,
    @SerializedName("upc")
    val upc: String?
)