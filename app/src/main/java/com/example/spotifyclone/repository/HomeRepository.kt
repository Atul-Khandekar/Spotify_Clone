package com.example.spotifyclone.repository

import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.service.HomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(private val homeService: HomeService) : BaseRepository() {

    suspend fun getFeaturedPlaylist() = flow {
        emit(BaseResponse.Loading())
        emit(handleResponse(homeService.getFeaturedPlaylists()))
    }.flowOn(Dispatchers.IO)

    suspend fun getAlbums() = flow {
        emit(BaseResponse.Loading())
        emit(handleResponse(homeService.getAlbums()))
    }.flowOn(Dispatchers.IO)

    suspend fun getUsersPlaylists() = flow {
        emit(BaseResponse.Loading())
        emit(handleResponse(homeService.getUsersPlaylists()))
    }.flowOn(Dispatchers.IO)

    suspend fun getFollowedArtists(map: Map<String, Any>) = flow {
        emit(BaseResponse.Loading())
        homeService.getFollowedArtists(map).also {
            val result = handleResponse(it)
            emit(result)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getRecentlyPlayedTrack() = flow {
        emit(BaseResponse.Loading())
        homeService.getRecentlyPlayedTrack().also {
            val result = handleResponse(it)
            emit(result)
        }
    }.flowOn(Dispatchers.IO)
}