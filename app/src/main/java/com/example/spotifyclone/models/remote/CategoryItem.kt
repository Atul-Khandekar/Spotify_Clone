package com.example.spotifyclone.models.remote

import com.google.gson.annotations.SerializedName

data class CategoryItem(
    @SerializedName("href") var href: String,
    @SerializedName("icons") var icons: List<Image>,
    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String,
)

data class CategoryResponse(
    @SerializedName("categories") val categories: Categories
)

data class Categories(
    @SerializedName("href") val href: String,
    @SerializedName("items") val items: List<CategoryItem>,
    @SerializedName("limit") val limit: Int,
    @SerializedName("next") val next: String,
    @SerializedName("offset") val offset: Int,
    @SerializedName("previous") val previous: String?,
    @SerializedName("total") val total: Int
)