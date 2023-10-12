package com.example.spotifyclone.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class Prefs (
    @ApplicationContext context: Context
) {

    private val preferences: SharedPreferences = getDefaultSharedPreferences(context)
    private val editor: SharedPreferences.Editor = preferences.edit()

    fun putString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    fun getString(key: String?, defValue: String): String {
        return preferences.getString(key, defValue) ?: defValue
    }

    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun getInt(key: String?, defValue: Int): Int {
        return runCatching {
            preferences.getInt(key, defValue)
        }.getOrElse { preferences.getLong(key, defValue.toLong()).toInt() }
    }

    fun getLong(key: String?, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }

    fun getFloat(key: String?, defValue: Float): Float {
        return preferences.getFloat(key, defValue)
    }

    fun clearPreferences() {
        editor.clear().apply()
    }

    private fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return getDefaultSharedPreferences(context)
    }
}