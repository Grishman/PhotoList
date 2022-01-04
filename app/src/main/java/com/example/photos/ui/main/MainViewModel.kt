package com.example.photos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entities.ApiResult
import com.example.entities.Photo
import com.example.photos.domain.GetAllPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(val useCase: GetAllPhotosUseCase) : ViewModel() {

    val photos = MutableSharedFlow<ApiResult<List<Photo>>>()

    fun fetchData() = viewModelScope.launch {
        useCase().collect {
            photos.emit(it)
        }
    }
}