package com.example.spotifyclone.repository

import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.service.TrackListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TrackListRepository @Inject constructor (private val trackListService: TrackListService): BaseRepository() {
    suspend fun getTrackList(playListId: String) = flow {
        emit(BaseResponse.Loading())
        emit(handleResponse(trackListService.getTrackList(playListId)))
    }.flowOn(Dispatchers.IO)

}