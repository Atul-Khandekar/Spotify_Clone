package com.example.spotifyclone.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.constants.AppConstants
import com.example.spotifyclone.models.local.MediaItemType
import com.example.spotifyclone.models.local.SearchData
import com.example.spotifyclone.models.remote.SearchResponse
import com.example.spotifyclone.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _searchData = MutableStateFlow<List<SearchData?>>(emptyList())
    val searchData: StateFlow<List<SearchData?>> get() = _searchData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private var searchJob: Job? = null
    private var limit = 25
    private var offset = 0

    fun search(text: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(AppConstants.DEBOUNCE_INTERVAL)
            if (text.isNullOrEmpty()) {
                _searchData.emit(emptyList())
                return@launch
            }
            searchForItems(text)
        }
    }

    private suspend fun searchForItems(query: String) {
        val map = mapOf<String, Any>(
            "q" to query,
            "type" to "album,track,playlist",
            "limit" to limit,
            "offset" to offset,
            "include_external" to true
        )

        searchRepository.searchForItem(map).collectLatest { response ->
            when (response) {
                is BaseResponse.Loading -> {
                    _isLoading.emit(true)
                }


                is BaseResponse.Success -> {
                    Log.d("success", "jhjhdfhjkljhgfdsfghjkljhgfdfghj")
                    response.data?.also {
                        val result = setData(it)
                        _isLoading.emit(false)

                        _searchData.emit(result)
                    }
                }

                is BaseResponse.Error -> {
                    _isLoading.emit(false)
                    _errorMessage.emit(response.msg)
                }
            }
        }
    }

    private fun setData(searchResponse: SearchResponse): List<SearchData?> {

        var list: MutableList<SearchData?> = mutableListOf()

        val tracks = searchResponse.tracks?.items?.map { track ->
            val artists = track.artists?.map { it.name }
            artists?.let {
                SearchData(
                    track.id ?: "",
                    track.album?.images?.firstOrNull()?.url,
                    track.name ?: "",
                    MediaItemType.Track(),
                    it.joinToString(", ")
                )
            }
        }
        tracks?.also {
            list.addAll(it)
        }

        val playlists = searchResponse.playlists?.items?.map { playlist ->

            val owner = playlist.owner?.displayName ?: "Unknown"
            SearchData(
                playlist.id?:"",
                playlist.images?.firstOrNull()?.url,
                playlist.name?:"",
                MediaItemType.Playlist(),
                owner
            )

        }
        playlists?.also {
            list.addAll(it)
        }

        val albums = searchResponse.albums?.items?.map { album ->
            val artists = album.artists?.map { it.name }
            artists?.let {
                SearchData(
                    album.id ?: "" ,
                    album.images?.firstOrNull()?.url,
                    album.name ?:"",
                    MediaItemType.Album() ,
                    it.joinToString(", ")
                )
            }
        }

        albums?.also {
            list.addAll(it)
        }

        Log.d("list", list.toString())
        return list
    }

}