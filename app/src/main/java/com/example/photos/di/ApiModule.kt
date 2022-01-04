package com.example.photos.di

import com.example.photos.data.ApiDataSource
import com.example.photos.network.PlaceholderApiService
import com.example.photos.data.RetrofitDataSource
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideApiDataSource(
        dataSource: RetrofitDataSource
    ): ApiDataSource = dataSource

    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        moshi: Moshi.Builder
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi.build()))
        .baseUrl(URL)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitOkHttpClient(
    ): OkHttpClient {
        val client = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        client.addInterceptor(interceptor)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideMoshiBuilder() = Moshi.Builder()

    @Provides
    @Singleton
    fun providePlaceholderApiService(
        retrofit: Retrofit
    ): PlaceholderApiService =
        retrofit.create(PlaceholderApiService::class.java)
}
