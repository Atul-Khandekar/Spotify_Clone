package com.example.spotifyclone.repository

import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.models.CurrentUserProfileResponseModel
import com.example.spotifyclone.service.CurrentUserService
import javax.inject.Inject

class CurrentUserRepository @Inject constructor(private val currentUserService: CurrentUserService): BaseRepository() {
    suspend fun getCurrentUserProfile(): BaseResponse<CurrentUserProfileResponseModel> = handleResponse(currentUserService.getCurrentUserProfile())
}