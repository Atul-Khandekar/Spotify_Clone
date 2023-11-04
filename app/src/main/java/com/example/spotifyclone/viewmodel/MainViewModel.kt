package com.example.spotifyclone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.constants.AppConstants
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.data.GrantType
import com.example.spotifyclone.repository.AuthRepository
import com.example.spotifyclone.repository.CurrentUserRepository
import com.example.spotifyclone.utils.Prefs
import com.example.spotifyclone.utils.PrefsKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefs: Prefs,
    private val currentUserRepository: CurrentUserRepository
) : ViewModel() {

    private val _accessToken = MutableLiveData<String?>(null)
    val accessToken get() = _accessToken

    private val _isLoading = MutableLiveData(false)
    val isLoading get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage get() = _errorMessage

    fun getAuthorizationToken(code: String) {
        viewModelScope.launch {
            val body = hashMapOf(
                "grant_type" to GrantType.AUTHORIZATION_CODE.value,
                "code" to code,
                "redirect_uri" to AppConstants.REDIRECT_URI
            )

            authRepository.getAuthorizationToken(body).let { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.value = true
                    }

                    is BaseResponse.Success -> {
                        response.data?.also { authResponse ->
                            authResponse.accessToken?.also { accessToken ->
                                prefs.putString(PrefsKeys.ACCESS_TOKEN, accessToken)
                            }

                            authResponse.refreshToken?.also { refreshToken ->
                                prefs.putString(PrefsKeys.REFRESH_TOKEN, refreshToken)
                            }

                            authResponse.expireTime?.also { time ->
                                prefs.putInt(
                                    PrefsKeys.EXPIRES_AT,
                                    Calendar.getInstance().get(Calendar.SECOND) + time
                                )
                            }

                            prefs.putBoolean(PrefsKeys.IS_LOGGED_IN, true)
                        }
//                        getCurrentUserProfile()
                        _isLoading.value = false
                        _accessToken.value = response.data?.accessToken
                    }

                    is BaseResponse.Error -> {
                        _isLoading.value = false
                        response.msg?.also { message ->
                            _errorMessage.value = message
                        }
                    }
                }
            }

        }
    }

    fun getLoginStatus(handler: (Boolean) -> Unit) {
        prefs.getString(PrefsKeys.ACCESS_TOKEN, "").apply {
            handler(this.isNotEmpty())
        }
    }

//
}