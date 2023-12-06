package com.example.spotifyclone.service

import com.example.spotifyclone.models.remote.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface SearchService {

    @GET("browse/categories")
    suspend fun getCategories(): Response<CategoryResponse>
}