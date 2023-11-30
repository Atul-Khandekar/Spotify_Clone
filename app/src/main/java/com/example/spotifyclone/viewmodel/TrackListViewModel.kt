package com.example.spotifyclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.models.local.DisplaySong
import com.example.spotifyclone.models.local.DisplaySongData
import com.example.spotifyclone.models.local.MediaItemType
import com.example.spotifyclone.repository.TrackListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackListViewModel @Inject constructor(private val trackListRepository: TrackListRepository) :
    ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _tracks = MutableStateFlow<DisplaySong?>(null)
    val tracks: StateFlow<DisplaySong?> get() = _tracks

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun getTrackList(playlistId: String) {
        viewModelScope.launch {
            trackListRepository.getTrackList(playlistId).collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.emit(true)
                    }

                    is BaseResponse.Success -> {
                        response.data?.also { songs ->
                            val songData = songs.tracks?.items?.map { item ->
                                val artists =
                                    item.track?.artists?.joinToString(", ") { it.name.toString() }
                                DisplaySongData(
                                    item.track?.name,
                                    artists,
                                    item.track?.album?.images?.firstOrNull()?.url,
                                    MediaItemType.Playlist(),
                                    item.track?.id,
                                    item.track?.durationMs
                                )
                            }

                            songData.let { songData ->
                                _isLoading.emit(false)
                                _tracks.emit(
                                    DisplaySong(
                                        MediaItemType.Playlist(),
                                        songs.images?.firstOrNull()?.url,
                                        songs.name,
                                        songs.owner?.displayName,
                                        songData
                                    )
                                )
                            }

                        }

                    }

                    is BaseResponse.Error -> {
                        _isLoading.emit(false)
                        _errorMessage.emit(response.msg)
                    }
                }
            }
        }
    }

}