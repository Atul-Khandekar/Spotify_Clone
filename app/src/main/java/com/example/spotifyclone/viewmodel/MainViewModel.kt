package com.example.spotifyclone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.AppConstants
import com.example.spotifyclone.data.GrantType
import com.example.spotifyclone.repository.AuthRepository
import com.example.spotifyclone.utils.Prefs
import com.example.spotifyclone.utils.PrefsKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val authRepository: AuthRepository , val prefs: Prefs)  : ViewModel() {

    private val _token = MutableLiveData<String?>(null)
    val token get() = _token

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

            authRepository.getAuthorizationToken(body)

        }
    }

}