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
}