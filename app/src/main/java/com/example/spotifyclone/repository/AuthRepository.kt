package com.example.spotifyclone.repository

import androidx.lifecycle.MutableLiveData
import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.data.AuthResponse
import com.example.spotifyclone.service.AuthService
import retrofit2.Response
import javax.inject.Inject

class AuthRepository @Inject constructor(private val authService: AuthService) : BaseRepository() {

    suspend fun getAuthorizationToken(body: HashMap<String, String>): BaseResponse<AuthResponse> {
        val response = authService.getAutherizationToken(body)
        return handleResponse(response)
    }
}