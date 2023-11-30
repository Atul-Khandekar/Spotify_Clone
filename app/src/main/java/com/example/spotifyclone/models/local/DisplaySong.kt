package com.example.spotifyclone.models.local

data class DisplaySong(
    val type: MediaItemType?,
    val images: String?,
    val name: String?,
    val owner: String?,
    val dataList: List<DisplaySongData>?
)

data class DisplaySongData(
    val songName: String?,
    val artistsName: String?,
    val image: String?,
    val type: MediaItemType,
    var id: String? = null,
    var songDuration: Int? = null,
    var title: String? = null,
    var subTitle: String? = null,
)

data class DisplayAlbumFooterView(
    val artistId: String = "",
    val releaseDate: String = "",
    val totalSongs: Int = 0,
    val copyRight: String = "",
    val artistImage: String = ""
)