package com.example.spotifyclone.models.remote


import com.google.gson.annotations.SerializedName

data class FeaturedPlaylistsResponseModel(
    @SerializedName("message")
    val message: String?,
    @SerializedName("playlists")
    val playlists: Playlists?
)

data class Playlists(
    @SerializedName("href")
    val href: String?,
    @SerializedName("items")
    val items: List<PlaylistItem>?,
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

data class PlaylistItem(
    @SerializedName("collaborative")
    val collaborative: Boolean?,
    @SerializedName("description")
    val description: String?,
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
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("public")
    val `public`: Boolean?,
    @SerializedName("snapshot_id")
    val snapshotId: String?,
    @SerializedName("tracks")
    val tracks: Tracks?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)

data class Tracks(
    @SerializedName("href")
    val href: String?,
    @SerializedName("total")
    val total: Int?
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