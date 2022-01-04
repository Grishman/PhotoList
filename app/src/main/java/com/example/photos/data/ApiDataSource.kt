package com.example.photos.data

import com.example.entities.ApiResult
import com.example.entities.Photo
import kotlinx.coroutines.flow.Flow

interface ApiDataSource {

    fun getAllPhotos(): Flow<ApiResult<List<Photo>>>
    fun getPhotoDetail(id: Int): Flow<ApiResult<Photo>>
}
