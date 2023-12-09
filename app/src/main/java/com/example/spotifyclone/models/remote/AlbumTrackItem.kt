package com.example.spotifyclone.models.remote

import com.google.gson.annotations.SerializedName

data class AlbumTrackItem(

    @SerializedName("artists") var artists: List<Artistsx>,
    @SerializedName("available_markets") var availableMarkets: List<String>,
    @SerializedName("disc_number") var discNumber: Int,
    @SerializedName("duration_ms") var durationMs: Int,
    @SerializedName("explicit") var explicit: Boolean,
    @SerializedName("external_urls") var externalUrls: ExternalUrls,
    @SerializedName("href") var href: String,
    @SerializedName("id") var id: String,
    @SerializedName("is_playable") var isPlayable: Boolean,
    @SerializedName("linked_from") var linkedFrom: LinkedFrom,
    @SerializedName("restrictions") var restrictions: Restrictions,
    @SerializedName("name") var name: String,
    @SerializedName("preview_url") var previewUrl: String,
    @SerializedName("track_number") var trackNumber: Int,
    @SerializedName("type") var type: String,
    @SerializedName("uri") var uri: String,
    @SerializedName("is_local") var isLocal: Boolean
)

data class LinkedFrom(

    @SerializedName("external_urls") var externalUrls: ExternalUrls,
    @SerializedName("href") var href: String,
    @SerializedName("id") var id: String,
    @SerializedName("type") var type: String,
    @SerializedName("uri") var uri: String

)