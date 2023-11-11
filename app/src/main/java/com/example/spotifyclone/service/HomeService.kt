package com.example.spotifyclone.service

import com.example.spotifyclone.models.remote.AlbumsResponseModel
import com.example.spotifyclone.models.remote.FeaturedPlaylistsResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {

    @GET("browse/featured-playlists")
    suspend fun getFeaturedPlaylists() : Response<FeaturedPlaylistsResponseModel>

    @GET("browse/new-releases")
    suspend fun getAlbums(): Response<AlbumsResponseModel>
}