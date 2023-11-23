package com.example.spotifyclone.models.local

data class HomePageData(val section:String , val items: List<HomePageItemData>)

data class HomePageItemData(val name: String, val id: String, val image: String, val type: MediaItemType)
