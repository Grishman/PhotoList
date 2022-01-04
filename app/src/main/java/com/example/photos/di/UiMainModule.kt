package com.example.photos.di

import android.app.Activity
import android.content.Context
import com.example.photos.navigation.NavigationHandler
import com.example.photos.navigation.NavigationHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
internal class UiMainModule {

    @Provides
    internal fun provideNavigationHandler(
        @ActivityContext context: Context,
    ): NavigationHandler = NavigationHandlerImpl(
        activity = context as Activity
    )
}