package com.example.spotifyclone.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.constants.AppConstants
import com.example.spotifyclone.repository.CurrentUserRepository
import com.example.spotifyclone.utils.Prefs
import com.example.spotifyclone.utils.PrefsKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    val prefs: Prefs,
    private val currentUserRepository: CurrentUserRepository
) : ViewModel() {

    private val _profileFetched = MutableLiveData(false)
    val profileFetched get() = _profileFetched

    private val _profileFetchingError = MutableLiveData<String?>(null)
    val profileFetchingError get() = _profileFetchingError

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
                                prefs.putString(AppConstants.USER_NAME,it)

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

}