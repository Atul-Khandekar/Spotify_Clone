package com.example.spotifyclone.service

import com.example.spotifyclone.models.local.TrackListData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TrackListService {

    @GET("playlists/{playlist_id}")
    suspend fun getTrackList( @Path ("playlist_id") id: String ) : Response<TrackListData>
}