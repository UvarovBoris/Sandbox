package com.uvarov.sandbox.ui.breed.list

import androidx.recyclerview.widget.RecyclerView
import com.uvarov.sandbox.databinding.BreedItemBinding
import com.uvarov.sandbox.model.Breed

class BreedVH(private val breedItemBinding: BreedItemBinding, private val breedClickListener: ((breed: Breed) -> Unit)) : RecyclerView.ViewHolder(breedItemBinding.root) {

    fun onBindViewHolder(breed: Breed) {
        breedItemBinding.breedNameTxt.text = breed.name

        breedItemBinding.root.setOnClickListener {
            breedClickListener.invoke(breed)
        }
    }
}