package com.example.spotifyclone.service

import com.example.spotifyclone.models.remote.CategoryResponse
import com.example.spotifyclone.models.remote.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SearchService {

    @GET("browse/categories")
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("search")
    @JvmSuppressWildcards
    suspend fun searchForItem(@QueryMap query: Map<String, Any>): Response<SearchResponse>
}