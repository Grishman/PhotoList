package com.example.photos.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.entities.ApiResult
import com.example.entities.Photo
import com.example.photos.domain.GetPhotoDetailsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    private val useCase: GetPhotoDetailsUseCase,
    @Assisted private val photoId: Int
) : ViewModel() {

    val photos = MutableSharedFlow<ApiResult<Photo>>()

    init {
        viewModelScope.launch {
            useCase.invoke(photoId).collect {
                photos.emit(it)
            }
        }

    }

    class Factory(
        private val assistedFactory: DetailsAssistedInjectFactory,
        private val photoId: Int
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(photoId) as T
        }
    }
}