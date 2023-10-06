package com.example.spotifyclone.service

import com.example.spotifyclone.data.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Accept: application/json")
    @POST("api/token")
    fun getAutherizationToken( @Body body: HashMap<String, String>): Response<AuthResponse>
}