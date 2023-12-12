package com.example.spotifyclone.models.remote

import com.example.spotifyclone.models.local.Track
import com.google.gson.annotations.SerializedName

data class ResponseRecentTrack(
    @SerializedName("href") var href: String,
    @SerializedName("limit") var limit: Int,
    @SerializedName("next") var next: String,
    @SerializedName("cursors") var cursors: Cursors,
    @SerializedName("total") var total: Int,
    @SerializedName("items") var items: List<Items>
)

data class Items(
    @SerializedName("track") var track: Track,
    @SerializedName("played_at") var playedAt: String,
    @SerializedName("context") var context: Context
)

data class Context(
    @SerializedName("type") var type: String,
    @SerializedName("href") var href: String,
    @SerializedName("external_urls") var externalUrls: ExternalUrls,
    @SerializedName("uri") var uri: String
)