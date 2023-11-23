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

data class Owner(
    @SerializedName("display_name")
    val displayName: String?,
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

data class Restrictions(
    @SerializedName("reason")
    val reason: String?
)

data class Followers(
    @SerializedName("href")
    val href: String?,
    @SerializedName("total")
    val total: Int?
)