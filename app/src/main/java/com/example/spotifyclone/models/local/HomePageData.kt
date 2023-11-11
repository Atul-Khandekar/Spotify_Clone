package com.example.spotifyclone.models.local

data class HomePageData(val section:String , val items: List<HomePageItem>)

data class HomePageItem(val name: String , val id: String , val image: String , val type: String)
