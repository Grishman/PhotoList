package com.example.photos.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

fun <T> Fragment.observe(state: Flow<T>, block: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        state.collectLatest { block(it) }
    }
}