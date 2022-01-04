package com.example.photos.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.entities.ApiResult
import com.example.photos.databinding.DetailsFragmentBinding
import com.example.photos.ui.BaseFragment
import com.example.photos.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFragment : BaseFragment<DetailsViewModel, DetailsFragmentBinding>() {

    @Inject
    lateinit var assistedFactory: DetailsAssistedInjectFactory

    private lateinit var binding: DetailsFragmentBinding
    private val args: DetailsFragmentArgs by navArgs()
    override val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Factory(assistedFactory, args.id)
    }

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DetailsFragmentBinding.inflate(inflater, container, false).also {
        binding = it
    }

    override fun bind(binding: DetailsFragmentBinding, savedInstanceState: Bundle?) {
        super.bind(binding, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        observe(viewModel.photos) {
            when (it) {
                is ApiResult.Error -> {
                }
                ApiResult.Loading -> {
                }
                is ApiResult.Success -> {
                    binding.ivPhoto.load(it.data.url)
                }
            }
        }
    }
}