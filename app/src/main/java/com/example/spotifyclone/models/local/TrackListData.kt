package com.example.spotifyclone.models.local


import com.example.spotifyclone.models.remote.ExternalUrls
import com.example.spotifyclone.models.remote.Followers
import com.example.spotifyclone.models.remote.Image
import com.example.spotifyclone.models.remote.Owner
import com.example.spotifyclone.models.remote.ResponseItem
import com.google.gson.annotations.SerializedName

data class TrackListData(
    @SerializedName("collaborative")
    val collaborative: Boolean?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls?,
    @SerializedName("followers")
    val followers: Followers?,
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
    val tracks:ResponseItem<Item>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("uri")
    val uri: String?
)