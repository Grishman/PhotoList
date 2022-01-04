package com.example.photos.ui.details

import dagger.assisted.AssistedFactory

@AssistedFactory
interface DetailsAssistedInjectFactory {
    fun create(photoId: Int): DetailsViewModel
}
