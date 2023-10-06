package com.example.spotifyclone.interceptor

import com.example.spotifyclone.AppConstants
import com.example.spotifyclone.utils.TokenManager
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if (chain.request().url.toString().startsWith(AppConstants.BASE_AUTH_URL)) {
            val credential = Credentials.basic(AppConstants.CLIENT_ID , AppConstants.CLIENT_SECRET)
            val request = chain.request().newBuilder().addHeader("Authorization", credential).build()
            return chain.proceed(request)
        }

        return chain.proceed(chain.request())
    }

}