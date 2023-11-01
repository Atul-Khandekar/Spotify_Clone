package com.example.spotifyclone.interceptor

import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.data.GrantType
import com.example.spotifyclone.repository.AuthRepository
import com.example.spotifyclone.utils.Prefs
import com.example.spotifyclone.utils.PrefsKeys
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Response
import okhttp3.Route
import java.util.Calendar
import okhttp3.Request as Request1

class ApiAuthenticator(
    private val authRepository: Lazy<AuthRepository>, private val prefs: Prefs
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request1? {
        // If Authorization token isn't present then provide access
        // token or try to generate new Authorization token if current
        // is expired.
        return if (response.request.headers["Authorization"] == null || regenerateToken()) {
            val accessToken = prefs.getString(PrefsKeys.ACCESS_TOKEN, "")
            response.request.newBuilder().header("Authorization", "Bearer $accessToken").build()
        } else {
            null
        }
    }

    private fun regenerateToken(): Boolean {
        val body = hashMapOf(
            "grant_type" to GrantType.REFRESH_TOKEN.value,
            "refresh_token" to prefs.getString(PrefsKeys.REFRESH_TOKEN, "")
        )


        return runBlocking {

            authRepository.get().refreshAccessToken(body).let { response ->
                when (response) {
                    is BaseResponse.Success -> {
                        val responseData = response.data ?: return@runBlocking false
                        prefs.apply {
                            responseData.accessToken?.also { putString(PrefsKeys.ACCESS_TOKEN, it) }
                            responseData.expireTime?.also {
                                putInt(
                                    PrefsKeys.EXPIRES_AT,
                                    Calendar.getInstance().get(Calendar.SECOND) + it
                                )
                            }
                        }
                        true
                    }

                    else -> {
                        false
                    }
                }

            }

        }

    }
}