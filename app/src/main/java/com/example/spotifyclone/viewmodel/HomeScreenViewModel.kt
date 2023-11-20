package com.example.spotifyclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.constants.AppConstants
import com.example.spotifyclone.constants.ImageConstants
import com.example.spotifyclone.constants.StringConstants
import com.example.spotifyclone.models.local.HomePageData
import com.example.spotifyclone.models.local.HomePageItem
import com.example.spotifyclone.repository.CurrentUserRepository
import com.example.spotifyclone.repository.HomeRepository
import com.example.spotifyclone.utils.Prefs
import com.example.spotifyclone.utils.PrefsKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val prefs: Prefs,
    private val currentUserRepository: CurrentUserRepository,
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _profileFetched = MutableStateFlow(false)
    val profileFetched: StateFlow<Boolean> get() = _profileFetched

    private val _profileFetchingError = MutableStateFlow<String?>(null)
    val profileFetchingError: StateFlow<String?> get() = _profileFetchingError

    private val _isLoading = MutableStateFlow<Boolean?>(null)
    val isLoading: StateFlow<Boolean?> get() = _isLoading

    private val _homePageList = MutableStateFlow<List<HomePageData?>?>(listOf())
    val homePageList: StateFlow<List<HomePageData?>?> get() = _homePageList

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun getHomeScreenData() {
        getFeaturedPlaylist()
        getAlbums()
    }

    fun getLoginStatus(handler: (Boolean) -> Unit) {
        prefs.getString(PrefsKeys.ACCESS_TOKEN, "").apply {
            handler(this.isNotEmpty())
        }
    }

    fun getCurrentUserProfile() {
        viewModelScope.launch {
            currentUserRepository.getCurrentUserProfile().collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {

                    }

                    is BaseResponse.Success -> {
                        response.data?.also { currentUserProfile ->

                            currentUserProfile.id?.also {
                                _profileFetched.emit(true)
                                prefs.putString(AppConstants.USER_ID, it)

                            }

                            currentUserProfile.displayName?.also {
                                prefs.putString(AppConstants.USER_NAME, it)

                            }
                        }

                    }

                    is BaseResponse.Error -> {
                        _profileFetched.emit(false)
                        _profileFetchingError.emit(response.msg)
                    }
                }
            }
        }
    }

    private fun getFeaturedPlaylist() {
        viewModelScope.launch {
            homeRepository.getFeaturedPlaylist().collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.emit(true)
                    }

                    is BaseResponse.Success -> {
                        response.data?.playlists?.also {

                            val list = it.items?.map { playlistItem ->
                                val imageUrl = playlistItem.images?.firstOrNull()?.url
                                    ?: ImageConstants.DEFAULT_SONG_BACKGROUND_IMAGE
                                HomePageItem(
                                    playlistItem.name ?: "",
                                    playlistItem.id ?: "",
                                    imageUrl,
                                    playlistItem.type ?: ""
                                )
                            }

                            _isLoading.emit(false)
                            list?.also { listOfItems ->

                                homePageList.value?.plus(
                                    HomePageData(
                                        StringConstants.FEATURED_PLAYLISTS, listOfItems
                                    )
                                ).let { it1 -> _homePageList.emit(it1) }
                            }

                        }
                    }

                    is BaseResponse.Error -> {
                        _errorMessage.emit(response.msg)
                        _isLoading.emit(false)
                    }
                }
            }
        }
    }

    private fun getAlbums() {
        viewModelScope.launch {
            homeRepository.getAlbums().collectLatest {response ->
                when(response) {
                    is BaseResponse.Loading -> {
                        _isLoading.emit(true)
                    }

                    is BaseResponse.Success -> {
                        response.data?.albums?.also {
                            val list = it.items?.map { albumItem ->
                                val imageUrl = albumItem.images?.firstOrNull()?.url
                                    ?: ImageConstants.DEFAULT_SONG_BACKGROUND_IMAGE
                                HomePageItem(
                                    albumItem.name ?: "",
                                    albumItem.id ?: "",
                                    imageUrl,
                                    albumItem.type ?: ""
                                )
                            }
                            _isLoading.emit(false)

                            list?.also { listOfItems ->

                                homePageList.value?.plus(
                                    HomePageData(
                                        StringConstants.ALBUMS, listOfItems
                                    )
                                ).let { it1 -> _homePageList.emit(it1) }
                            }

                        }

                    }

                    is BaseResponse.Error -> {
                        _errorMessage.emit(response.msg)
                        _isLoading.emit(false)
                    }

                }
            }
        }
    }
}