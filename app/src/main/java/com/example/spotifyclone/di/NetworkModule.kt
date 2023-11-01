package com.example.spotifyclone.di

import android.content.Context
import com.example.spotifyclone.AppConstants
import com.example.spotifyclone.AppConstants.API_RETROFIT
import com.example.spotifyclone.AppConstants.AUTH_RETROFIT
import com.example.spotifyclone.AppConstants.NETWORK_TIMEOUT
import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.interceptor.ApiAuthenticator
import com.example.spotifyclone.interceptor.BasicAuthInterceptor
import com.example.spotifyclone.repository.AuthRepository
import com.example.spotifyclone.service.AuthService
import com.example.spotifyclone.utils.Prefs
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.picasso.BuildConfig
import dagger.Module
import dagger.Lazy
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesLoggingIntercepter(): HttpLoggingInterceptor = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    } else {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE }
    }

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @Provides
    @Singleton
    fun providesBasicAuthIntercepter(): BasicAuthInterceptor = BasicAuthInterceptor()

    @Provides
    @Singleton
    fun providesApiAuthenticator(
        authRepository: Lazy<AuthRepository>, preferenceHelper: Prefs
    ): ApiAuthenticator = ApiAuthenticator(authRepository, preferenceHelper)

    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: BasicAuthInterceptor,
        apiAuthenticator: ApiAuthenticator
    ): OkHttpClient = OkHttpClient.Builder().readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS).addInterceptor(loggingInterceptor)
        .addInterceptor(authInterceptor).authenticator(apiAuthenticator).build()


    @Provides
    @Singleton
    @Named(AUTH_RETROFIT)
    fun providesAuthRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(AppConstants.BASE_AUTH_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    @Named(API_RETROFIT)
    fun providesApiRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(AppConstants.BASE_API_URL + AppConstants.VERSION).client(client)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()

    @Provides
    @Singleton
    fun providesSharedPreference(@ApplicationContext context: Context): Prefs = Prefs(context)

    @Singleton
    @Provides
    fun providesAuthService(@Named(AUTH_RETROFIT) retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesAuthRepository(authService: AuthService): AuthRepository = AuthRepository(authService)

    @Provides
    @Singleton
    fun providesBaseRepository(): BaseRepository = BaseRepository()
}