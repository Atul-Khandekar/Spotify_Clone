package com.example.spotifyclone.models.local

import com.example.spotifyclone.constants.StringConstants

sealed class MediaItemType {
    data class Album(val value: String = StringConstants.ALBUM): MediaItemType()
    data class Playlist(val value: String = StringConstants.PLAYLIST): MediaItemType()

}
