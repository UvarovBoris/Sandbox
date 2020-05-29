package com.uvarov.sandbox.ui.breed.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.api.BreedsResponse
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.model.Breed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class BreedsListViewModel constructor(private val dogService: DogService) : ViewModel() {

    val breedsLD: MutableLiveData<List<Breed>> = MutableLiveData()

    fun requestBreeds() {
        dogService.getBreeds().enqueue(object : Callback<BreedsResponse> {
            override fun onResponse(call: Call<BreedsResponse>, response: Response<BreedsResponse>) {
                breedsLD.value = response.body()?.breeds
            }

            override fun onFailure(call: Call<BreedsResponse>, t: Throwable) {
                Timber.e(t)
            }
        })
    }
}
