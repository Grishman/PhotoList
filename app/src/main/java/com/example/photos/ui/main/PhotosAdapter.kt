package com.example.photos.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.entities.Photo
import com.example.photos.databinding.ItemPhotoBinding

class PhotosAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<Photo, RecyclerView.ViewHolder>(PhotosDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPhotoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PhotoViewHolder -> {
                val item = getItem(position)
                holder.binding.apply {
                    tvTitle.text = item.title
                    ivPhoto.load(item.thumbnailUrl)
                    root.setOnClickListener {
                        onClick.invoke(item.id)
                    }
                }
            }
            else -> throw IllegalStateException("Not implemented: $holder.")
        }
    }

    inner class PhotoViewHolder(val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root)

    class PhotosDiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.id == newItem.id &&
                    oldItem.albumId == newItem.albumId &&
                    oldItem.url == newItem.url
        }

        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
}

