package com.example.photos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.photos.navigation.NavigationHandler
import javax.inject.Inject

abstract class BaseFragment <VM : ViewModel, VB : ViewBinding> : Fragment() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    protected abstract val viewModel: VM

    abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = createBinding(inflater, container).apply { bind(this, savedInstanceState) }.root

    @CallSuper
    open fun bind(binding: VB, savedInstanceState: Bundle?) = Unit
}