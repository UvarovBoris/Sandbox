package com.uvarov.sandbox.ui.breed.detail

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uvarov.sandbox.databinding.BreedImageItemBinding
import com.uvarov.sandbox.model.BreedImage

class BreedImageVH(private val breedImageItemBinding: BreedImageItemBinding) : RecyclerView.ViewHolder(breedImageItemBinding.root) {

    fun onBindViewHolder(breedImage: BreedImage) {
        Glide
            .with(breedImageItemBinding.root)
            .load(breedImage.path)
            .centerCrop()
            .into(breedImageItemBinding.breedImg)
    }
}