package com.example.spotifyclone.base

import com.example.spotifyclone.data.ApiError
import com.google.gson.Gson
import retrofit2.Response

class BaseRepository {

    fun <T> handleResponse(response: Response<T>): BaseResponse<T> {
        return try {
            if (response.isSuccessful) {
                BaseResponse.Success(response.body())
            } else {
                if (response.code() in 400..499) {
                    response.errorBody().let {
                        val errorResponse =
                            Gson().fromJson(response.errorBody()?.string(), ApiError::class.java)
                        BaseResponse.Error(errorResponse.message ?: "Something Went Wrong")
                    }
                } else {
                    BaseResponse.Error(response.message())
                }
            }

        } catch (e: Error) {
            BaseResponse.Error(e.message ?: "Something went wrong")
        }
    }
}