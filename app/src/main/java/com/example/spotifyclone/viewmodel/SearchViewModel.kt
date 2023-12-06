package com.example.spotifyclone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spotifyclone.base.BaseResponse
import com.example.spotifyclone.models.remote.CategoryItem
import com.example.spotifyclone.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _categoryList = MutableStateFlow<List<CategoryItem>>(emptyList())
    val categoryList: StateFlow<List<CategoryItem>> get() = _categoryList

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            searchRepository.getCategories().collectLatest { response ->
                when (response) {
                    is BaseResponse.Loading -> {
                        _isLoading.emit(true)
                    }

                    is BaseResponse.Success -> {
                        _isLoading.emit(false)

                        response.data?.also {
                            _categoryList.emit(it.categories.items)
                        }
                    }

                    is BaseResponse.Error -> {
                        _isLoading.emit(false)
                        _errorMessage.emit(response.msg)
                    }
                }

            }
        }

    }
}