package com.example.spotifyclone.models.remote

import com.google.gson.annotations.SerializedName

data class ResponseItem<T>(
    @SerializedName("href")
    val href: String?,
    @SerializedName("items")
    val items: List<T>?,
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
