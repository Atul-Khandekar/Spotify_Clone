package com.example.spotifyclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.constants.AppConstants
import com.example.spotifyclone.data.GrantType
import com.example.spotifyclone.repository.AuthRepository
import com.example.spotifyclone.utils.Prefs
import com.example.spotifyclone.utils.PrefsKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val prefs: Prefs,
) : ViewModel() {

    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> get() = _accessToken

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    fun getAuthorizationToken(code: String) {
        viewModelScope.launch {
            val body = hashMapOf(
                "grant_type" to GrantType.AUTHORIZATION_CODE.value,
                "code" to code,
                "redirect_uri" to AppConstants.REDIRECT_URI
            )

            authRepository.getAuthorizationToken(body).collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.emit(true)
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
                        _isLoading.emit(false)
                        _accessToken.emit(response.data?.accessToken)
                    }

                    is BaseResponse.Error -> {
                        _isLoading.emit(false)
                        _errorMessage.emit(response.msg)
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
}