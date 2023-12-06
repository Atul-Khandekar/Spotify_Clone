package com.example.spotifyclone.repository

import com.example.spotifyclone.base.BaseRepository
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.service.SearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepository @Inject constructor(private val searchService: SearchService) :
    BaseRepository() {

    suspend fun getCategories() = flow {
        emit(BaseResponse.Loading())
        searchService.getCategories().let {
            val response = handleResponse(it)
            emit(response)
        }
    }.flowOn(Dispatchers.IO)
}