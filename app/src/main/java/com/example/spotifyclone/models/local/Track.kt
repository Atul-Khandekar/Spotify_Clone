package com.example.spotifyclone.models.local


import com.example.spotifyclone.models.remote.AlbumItem
import com.example.spotifyclone.models.remote.ExternalUrls
import com.example.spotifyclone.models.remote.Restrictions
import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("album")
    val album: AlbumItem?,
    @SerializedName("artists")
    val artists: List<ArtistX>?,
    @SerializedName("available_markets")
    val availableMarkets: List<String>?,
    @SerializedName("disc_number")
    val discNumber: Int?,
    @SerializedName("duration_ms")
    val durationMs: Int?,
    @SerializedName("explicit")
    val explicit: Boolean?,
    @SerializedName("external_ids")
    val externalIds: ExternalIds?,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls?,
    @SerializedName("href")
    val href: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("is_local")
    val isLocal: Boolean?,
    @SerializedName("is_playable")
    val isPlayable: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("popularity")
    val popularity: Int?,
    @SerializedName("preview_url")
    val previewUrl: String?,
    @SerializedName("restrictions")
    val restrictions: Restrictions?,
    @SerializedName("track_number")
    val trackNumber: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)