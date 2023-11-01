package com.example.spotifyclone.repository

import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.data.AuthResponse
import com.example.spotifyclone.service.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService) : BaseRepository() {

    suspend fun getAuthorizationToken(body: HashMap<String, String>): BaseResponse<AuthResponse> =
        handleResponse(authService.getAuthorizationToken(body))

    suspend fun refreshAccessToken(body: HashMap<String, String>): BaseResponse<AuthResponse> =
        handleResponse(authService.refreshAccessToken(body))

}