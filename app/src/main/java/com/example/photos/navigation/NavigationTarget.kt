package com.example.photos.navigation

sealed interface NavigationTarget{

    object Back : NavigationTarget
    data class PhotosDetails(val id: Int) : NavigationTarget
}