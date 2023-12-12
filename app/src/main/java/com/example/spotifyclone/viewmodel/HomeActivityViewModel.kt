package com.example.spotifyclone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.constants.AppConstants
import com.example.spotifyclone.constants.StringConstants
import com.example.spotifyclone.models.local.RecentlyPlayedTrack
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
class HomeActivityViewModel @Inject constructor(
    val prefs: Prefs,
    private val currentUserRepository: CurrentUserRepository,
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _profileFetched = MutableStateFlow(false)
    val profileFetched: StateFlow<Boolean> get() = _profileFetched

    private val _profileFetchingError = MutableStateFlow<String?>(null)
    val profileFetchingError: StateFlow<String?> get() = _profileFetchingError

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    val recentTrackList = MutableLiveData<RecentlyPlayedTrack?>(null)

    fun getLoginStatus(handler: (Boolean) -> Unit) {
        prefs.getString(PrefsKeys.ACCESS_TOKEN, "").apply {
            handler(this.isNotEmpty())
        }
    }

    init {
        getRecentlyPlayedTrack()
    }

    private fun getRecentlyPlayedTrack() {
        viewModelScope.launch {
            homeRepository.getRecentlyPlayedTrack().collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        // loading state
                    }

                    is BaseResponse.Success -> {
                        response.data?.also {
                            val track = it.items.map { item ->
                                val artists = item.track.artists?.map { artist -> artist.name }
                                    ?.joinToString(", ")

                                RecentlyPlayedTrack(
                                    item.track.id ?: StringConstants.UNKNOWN,
                                    item.track.name ?: StringConstants.UNKNOWN,
                                    artists ?: StringConstants.UNKNOWN,
                                    item.track.album?.images?.firstOrNull()?.url
                                        ?: StringConstants.UNKNOWN
                                )
                            }
                            recentTrackList.value = track.first()

                        }
                    }

                    is BaseResponse.Error -> {
                        _errorMessage.emit(response.msg)
                    }
                }
            }
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
}