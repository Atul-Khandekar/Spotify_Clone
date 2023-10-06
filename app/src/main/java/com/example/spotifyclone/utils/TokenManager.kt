package com.example.spotifyclone.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.spotifyclone.AppConstants.PREFS_FILE
import com.example.spotifyclone.AppConstants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILE , Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN , token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}