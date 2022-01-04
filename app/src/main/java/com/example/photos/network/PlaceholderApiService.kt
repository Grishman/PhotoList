package com.example.photos.network

import com.example.photos.network.models.ApiPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceholderApiService {

    @GET("photos")
    suspend fun getPhotos(): Response<List<ApiPhoto>>

    @GET("photos/{photoId}")
    suspend fun getPhoto(@Path("photoId") photoId: Int): Response<ApiPhoto>
}