package com.example.spotifyclone.models.local

import android.os.Parcelable
import com.example.spotifyclone.constants.StringConstants
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class MediaItemType : Parcelable {

    data class Album(val value: String = StringConstants.ALBUM) : MediaItemType()

    data class Playlist(val value: String = StringConstants.PLAYLIST) : MediaItemType()

    data class Track(val value: String = StringConstants.SONG) : MediaItemType()

    data class Artist(val value: String = StringConstants.ARTIST) : MediaItemType()

}
