package com.example.spotifyclone.models.remote


import com.google.gson.annotations.SerializedName

data class AlbumsResponseModel(
    @SerializedName("albums")
    val albums: Albums?
)

data class Albums(
    @SerializedName("href")
    val href: String?,
    @SerializedName("items")
    val items: List<AlbumItem>?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("total")
    val total: Int?
)

data class AlbumItem(
    @SerializedName("album_type")
    val albumType: String?,
    @SerializedName("artists")
    val artists: List<Artistsx>?,
    @SerializedName("available_markets")
    val availableMarkets: List<String>?,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: List<Image>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("release_date_precision")
    val releaseDatePrecision: String?,
    @SerializedName("restrictions")
    val restrictions: Restrictions?,
    @SerializedName("total_tracks")
    val totalTracks: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)

data class Artistsx(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)