package com.example.photos.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.photos.navigation.NavigationTarget
import com.example.photos.databinding.MainFragmentBinding
import com.example.photos.ui.BaseFragment
import com.example.photos.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, MainFragmentBinding>() {

    private lateinit var binding: MainFragmentBinding
    override val viewModel: MainViewModel by viewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = MainFragmentBinding.inflate(inflater, container, false).also {
        binding = it
    }

    override fun bind(binding: MainFragmentBinding, savedInstanceState: Bundle?) {
        super.bind(binding, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val decorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val photosAdapter = PhotosAdapter { id ->
            //navigate to details
            navigationHandler.navigateTo(NavigationTarget.PhotosDetails(id))
        }

        binding.rvPhotos.adapter = photosAdapter
        binding.rvPhotos.addItemDecoration(decorator)
        viewModel.fetchData()
        observe(viewModel.photos) {
            when (it) {
                is com.example.entities.ApiResult.Error -> {
                    binding.tvError.text = it.message
                    binding.tvError.visibility = View.VISIBLE
                }
                com.example.entities.ApiResult.Loading -> {
                    binding.tvError.visibility = View.GONE
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is com.example.entities.ApiResult.Success -> {
                    binding.tvError.visibility = View.GONE
                    binding.pbLoading.visibility = View.GONE
                    photosAdapter.submitList(it.data)
                }
            }
        }
    }
}