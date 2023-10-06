package com.example.spotifyclone.data

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("access_token")
    val accessToken: String?,
    @SerializedName("refresh_token")
    val refreshToken: String?,
    @SerializedName("token_type")
    val tokenType: String?,
    @SerializedName("expire_time")
    val expireTime: Int?,
    @SerializedName("scope")
    val scope: String?
)