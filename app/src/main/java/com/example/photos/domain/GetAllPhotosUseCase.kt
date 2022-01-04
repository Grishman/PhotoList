package com.example.photos.domain

import com.example.photos.data.ApiDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllPhotosUseCase @Inject constructor(private val dataSource: ApiDataSource) {

    suspend operator fun invoke() = dataSource.getAllPhotos()
}