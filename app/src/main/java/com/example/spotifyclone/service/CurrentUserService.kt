package com.example.spotifyclone.service

import com.example.spotifyclone.models.remote.CurrentUserProfileResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface CurrentUserService {
    @GET("me")
    suspend fun getCurrentUserProfile(): Response<CurrentUserProfileResponseModel>
}