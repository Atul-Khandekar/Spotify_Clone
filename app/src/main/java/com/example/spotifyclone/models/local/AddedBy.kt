package com.example.spotifyclone.models.local


import com.example.spotifyclone.models.remote.ExternalUrls
import com.example.spotifyclone.models.remote.Followers
import com.google.gson.annotations.SerializedName

data class AddedBy(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls?,
    @SerializedName("followers")
    val followers: Followers?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)