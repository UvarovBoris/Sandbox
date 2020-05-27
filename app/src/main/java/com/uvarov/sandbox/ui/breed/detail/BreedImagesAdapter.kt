package com.uvarov.sandbox.ui.breed.detail;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uvarov.sandbox.databinding.BreedImageItemBinding
import com.uvarov.sandbox.model.BreedImage

class BreedImagesAdapter : RecyclerView.Adapter<BreedImageVH>() {

    var breedImages: List<BreedImage>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = breedImages?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedImageVH {
        val breedImageVB = BreedImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedImageVH(breedImageVB)
    }

    override fun onBindViewHolder(holder: BreedImageVH, position: Int) {
        breedImages?.get(position)?.let {
            holder.onBindViewHolder(it)
        }
    }
}