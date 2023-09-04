package com.example.spotifyclone.models

import java.util.UUID


data class HomeScreenModel(val homeScreenList: List<AlbumModel> , val id: UUID, val sectionName: String)
