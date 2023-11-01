package com.example.spotifyclone.service

import com.example.spotifyclone.data.AuthResponse
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Accept: application/json")
    @POST("api/token")
    @FormUrlEncoded
    suspend fun getAuthorizationToken(@FieldMap body: HashMap<String, String>): Response<AuthResponse>

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("api/token")
    suspend fun refreshAccessToken(@FieldMap body: HashMap<String, String>): Response<AuthResponse>
}