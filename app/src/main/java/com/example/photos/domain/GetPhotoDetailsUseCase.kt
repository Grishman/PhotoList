package com.example.photos.domain

import com.example.photos.data.ApiDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPhotoDetailsUseCase @Inject constructor(private val dataSource: ApiDataSource) {

    suspend operator fun invoke(id: Int) = dataSource.getPhotoDetail(id)
}