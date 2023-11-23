package com.example.spotifyclone.models.local


import com.example.spotifyclone.models.remote.ExternalUrls
import com.example.spotifyclone.models.remote.Followers
import com.example.spotifyclone.models.remote.Image
import com.google.gson.annotations.SerializedName

data class ArtistX(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls?,
    @SerializedName("followers")
    val followers: Followers?,
    @SerializedName("genres")
    val genres: List<String>?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("popularity")
    val popularity: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)