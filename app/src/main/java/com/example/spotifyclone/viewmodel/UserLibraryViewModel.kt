package com.example.spotifyclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.constants.ImageConstants
import com.example.spotifyclone.models.local.LibraryItem
import com.example.spotifyclone.models.local.MediaItemType
import com.example.spotifyclone.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLibraryViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {

    private val _isLoading = MutableStateFlow<Boolean?>(null)
    val isLoading: StateFlow<Boolean?> get() = _isLoading

    private val _libraryItem = MutableStateFlow<List<LibraryItem>>(emptyList())
    val libraryItem: StateFlow<List<LibraryItem>> get() = _libraryItem

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private  var libraryItemList: List<LibraryItem> = emptyList()
    init {
        getUsersPlaylist()
        getFollowedArtists()
    }

    private fun getUsersPlaylist() {
        viewModelScope.launch {
            homeRepository.getUsersPlaylists().collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.emit(true)
                    }

                    is BaseResponse.Success -> {
                        response.data?.also {
                            val list = it.items?.map { playlistItem ->
                                val imageUrl = playlistItem.images?.firstOrNull()?.url
                                    ?: ImageConstants.DEFAULT_SONG_BACKGROUND_IMAGE
                                LibraryItem(
                                    playlistItem.id,
                                    imageUrl,
                                    playlistItem.name,
                                    MediaItemType.Playlist(),
                                    playlistItem.owner?.displayName
                                )
                            }

                            list?.also { item ->
                                _isLoading.emit(false)
                                _libraryItem.value += list
                                libraryItemList = _libraryItem.value
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

    private fun getFollowedArtists() {
        val map = mapOf(
            "type" to "artist"
        )
        viewModelScope.launch {
            homeRepository.getFollowedArtists(map).collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.emit(true)
                    }

                    is BaseResponse.Success -> {
                        response.data?.artists?.also { artists ->
                            val list = artists.items.map {
                                LibraryItem(
                                    it.id,
                                    it.images?.firstOrNull()?.url,
                                    it.name,
                                    MediaItemType.Artist(),
                                    ""
                                )
                            }
                            _isLoading.emit(false)
                            _libraryItem.value += list
                            libraryItemList = _libraryItem.value
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

    suspend fun playlistChecked() {
        _libraryItem.emit(_libraryItem.value.filterNot {
            it.type == MediaItemType.Artist()
        })
    }

    suspend fun uncheckedList() {
        _libraryItem.emit(libraryItemList)
    }

    suspend fun artistChecked() {
        _libraryItem.emit(
            _libraryItem.value.filterNot {
                it.type == MediaItemType.Playlist()
            }
        )
    }

}