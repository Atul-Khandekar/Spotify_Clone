package com.example.spotifyclone.interceptor

import com.example.spotifyclone.constants.AppConstants
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        if (chain.request().url.toString().startsWith(AppConstants.BASE_AUTH_URL)) {
            val credential = Credentials.basic(AppConstants.CLIENT_ID , AppConstants.CLIENT_SECRET)
            val request = chain.request().newBuilder().addHeader("Authorization", credential).addHeader("Content-Type",
                AppConstants.CONTENT_TYPE ).build()
            return chain.proceed(request)
        }

        return chain.proceed(chain.request())
    }

}