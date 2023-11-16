package com.example.spotifyclone.repository

import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.service.CurrentUserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CurrentUserRepository @Inject constructor(private val currentUserService: CurrentUserService) :
    BaseRepository() {
    suspend fun getCurrentUserProfile() = flow {
        emit(BaseResponse.Loading())
        emit(handleResponse(currentUserService.getCurrentUserProfile()))

    }.flowOn(Dispatchers.IO)
}