package com.example.spotifyclone.service

import com.example.spotifyclone.models.remote.AlbumsResponseModel
import com.example.spotifyclone.models.remote.ArtistResponse
import com.example.spotifyclone.models.remote.FeaturedPlaylistsResponseModel
import com.example.spotifyclone.models.remote.PlaylistItem
import com.example.spotifyclone.models.remote.ResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface HomeService {

    @GET("browse/featured-playlists")
    suspend fun getFeaturedPlaylists() : Response<FeaturedPlaylistsResponseModel>

    @GET("browse/new-releases")
    suspend fun getAlbums(): Response<AlbumsResponseModel>

    @GET("me/playlists")
    suspend fun getUsersPlaylists(): Response<ResponseItem<PlaylistItem>>

    @GET("me/following")
    @JvmSuppressWildcards
    suspend fun getFollowedArtists(@QueryMap map: Map<String, Any>): Response<ArtistResponse>
}