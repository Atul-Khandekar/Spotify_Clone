package com.example.spotifyclone

object AppConstants {
    const val BASE_AUTH_URL = "https://accounts.spotify.com/"
    const val BASE_API_URL = "https://api.spotify.com/"

    const val API = "api/"
    const val VERSION = "v1/"

    const val CLIENT_ID = "2cddbb6254b04888be0310447f33b6d6"
    const val CLIENT_SECRET = "03cfd3bd01744f7a9b422ecf69b5c67b"
    const val STATE = "XYZWabcdEFGH"
    const val REDIRECT_URI = "spotify://com.spotifyclone"
    const val SCOPES = "ugc-image-upload user-read-playback-state user-modify-playback-state user-read-currently-playing app-remote-control streaming playlist-read-private playlist-read-collaborative playlist-modify-private playlist-modify-public user-follow-modify user-follow-read user-read-playback-position user-top-read user-read-recently-played user-library-modify user-library-read user-read-email user-read-private"

    const val PREFS_FILE = "pref_file"
    const val USER_TOKEN = "user_token"

    const val CONTENT_TYPE = "application/x-www-form-urlencoded"

    const val CODE = "code"
    const val AUTH_RETROFIT = "AUTH_RETROFIT"
    const val API_RETROFIT = "API_RETROFIT"
    const val NETWORK_TIMEOUT: Long = 60
}

