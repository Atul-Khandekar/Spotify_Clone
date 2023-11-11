package com.example.spotifyclone.repository

import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.service.HomeService
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeService: HomeService): BaseRepository() {

    suspend fun getFeaturedPlaylist() = handleResponse(homeService.getFeaturedPlaylists())

    suspend fun getAlbums() = handleResponse(homeService.getAlbums())
}