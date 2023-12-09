package com.example.spotifyclone.models.remote

import com.example.spotifyclone.models.local.Artists
import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("artists") var artists: FollowedArtists
)

data class FollowedArtists(
    @SerializedName("href") var href: String,
    @SerializedName("limit") var limit: Int,
    @SerializedName("next") var next: String,
    @SerializedName("cursors") var cursors: Cursors,
    @SerializedName("total") var total: Int,
    @SerializedName("items") var items: List<Artists>
)

data class Cursors(
    @SerializedName("after") var after: String,
    @SerializedName("before") var before: String
)