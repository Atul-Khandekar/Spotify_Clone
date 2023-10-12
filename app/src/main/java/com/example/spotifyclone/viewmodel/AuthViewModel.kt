package com.example.spotifyclone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.data.AuthResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: BaseRepository) : ViewModel() {

}