package com.example.photos.network

import com.example.entities.Photo
import com.example.photos.network.models.ApiPhoto

fun List<ApiPhoto>.toEntity(): List<Photo> = this.map { it.toEntity() }

fun ApiPhoto.toEntity(): Photo =
    Photo(
        albumId = this.albumId,
        id = this.id,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl
    )