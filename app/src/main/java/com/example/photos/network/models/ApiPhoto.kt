package com.example.photos.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiPhoto(
    @field:Json(name = "albumId") val albumId: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "thumbnailUrl") val thumbnailUrl: String
)