package com.example.spotifyclone.di

import com.example.spotifyclone.AppConstants
import com.example.spotifyclone.service.AuthService
import com.squareup.picasso.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class )
object NetworkModule {

    fun providesLoggingIntercepter(): HttpLoggingInterceptor =  if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    } else {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE }
    }

//    fun providesRetrofit(): Retrofit = Retrofit.Builder().baseUrl(AppConstants.BASE_AUTH_URL)

    @Provides
    @Singleton
    fun providesAuthService( retrofit: Retrofit) = retrofit.create(AuthService::class.java)
}