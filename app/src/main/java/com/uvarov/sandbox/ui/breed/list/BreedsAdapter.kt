package com.uvarov.sandbox.ui.breed.list;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uvarov.sandbox.databinding.BreedItemBinding
import com.uvarov.sandbox.model.Breed

class BreedsAdapter(private val breedClickListener: (Breed) -> Unit) : RecyclerView.Adapter<BreedVH>() {

    var breeds: List<Breed>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = breeds?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedVH {
        val breedVB = BreedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedVH(breedVB, breedClickListener)
    }

    override fun onBindViewHolder(holder: BreedVH, position: Int) {
        breeds?.get(position)?.let {
            holder.onBindViewHolder(it)
        }
    }
}