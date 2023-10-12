package com.example.spotifyclone.service

import com.example.spotifyclone.data.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {

    @Headers("Content-Type: application/json")
    @POST("api/token")
    @FormUrlEncoded
    fun getAutherizationToken( @FieldMap body: HashMap<String, String>): Response<AuthResponse>
}