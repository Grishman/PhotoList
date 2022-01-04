package com.example.photos.navigation

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.photos.R
import com.example.photos.ui.main.MainFragmentDirections
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavigationHandlerImpl @Inject constructor(
    private val activity: Activity,
) : NavigationHandler {
    private val navController: NavController by lazy {
        activity.findNavController(R.id.nav_host_fragment)
    }

    override fun navigateTo(target: NavigationTarget) {
        when (target) {
            NavigationTarget.Back -> {
                navController.popBackStack()
            }
            is NavigationTarget.PhotosDetails -> {
                val action = MainFragmentDirections.actionToDetailsFragment(target.id)
                navigate(action)
            }
            else -> throw IllegalArgumentException()
        }
    }

    private fun navigate(action: NavDirections) {
        navController.navigate(action)
    }
}
