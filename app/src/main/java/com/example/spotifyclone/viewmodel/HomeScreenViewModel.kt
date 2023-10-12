package com.example.spotifyclone.viewmodel

import androidx.lifecycle.ViewModel
import com.example.spotifyclone.utils.Prefs
import com.example.spotifyclone.utils.PrefsKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(val prefs: Prefs) : ViewModel() {

    fun getLoginStatus(handler: (Boolean) -> Unit) {
        prefs.getString(PrefsKeys.ACCESS_TOKEN, "").apply {
            handler(this.isNotEmpty())
        }
    }
}