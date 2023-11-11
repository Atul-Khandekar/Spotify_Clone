package com.example.spotifyclone.viewmodel

import androidx.lifecycle.MutableLiveData
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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val prefs: Prefs,
    private val currentUserRepository: CurrentUserRepository,
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _profileFetched = MutableLiveData(false)
    val profileFetched get() = _profileFetched

    private val _profileFetchingError = MutableLiveData<String?>(null)
    val profileFetchingError get() = _profileFetchingError

    private val _isLoading = MutableLiveData(false)
    val isLoading get() = _isLoading

    private val _homePageList = MutableLiveData<List<HomePageData>>(listOf())
    val homePageList = _homePageList

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage get() = _errorMessage

    init {
        getFeaturedPlaylist()
    }

    fun getLoginStatus(handler: (Boolean) -> Unit) {
        prefs.getString(PrefsKeys.ACCESS_TOKEN, "").apply {
            handler(this.isNotEmpty())
        }
    }

    fun getCurrentUserProfile() {
        viewModelScope.launch {
            currentUserRepository.getCurrentUserProfile().also { response ->
                when (response) {
                    is BaseResponse.Success -> {
                        response.data?.also { currentUserProfile ->

                            currentUserProfile.id?.also {
                                _profileFetched.value = true
                                prefs.putString(AppConstants.USER_ID, it)

                            }

                            currentUserProfile.displayName?.also {
                                prefs.putString(AppConstants.USER_NAME, it)

                            }
                        }

                    }

                    is BaseResponse.Error -> {
                        _profileFetched.value = false
                        _profileFetchingError.value = response.msg
                    }

                    else -> {

                    }
                }
            }
        }
    }

    fun getFeaturedPlaylist() {
        viewModelScope.launch {
            homeRepository.getFeaturedPlaylist().also { response ->
                when (response) {

                    is BaseResponse.Loading -> {
                        _isLoading.value = true
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
                            list?.also { listOfItems ->
                                _homePageList.value = _homePageList.value?.plus(
                                    HomePageData(
                                        StringConstants.FEATURED_PLAYLISTS, listOfItems
                                    )
                                )
                            }
                            _isLoading.value = false

                        }
                    }

                    is BaseResponse.Error -> {
                        _errorMessage.value = response.msg
                        _isLoading.value = false
                    }
                }
            }
        }
    }
}