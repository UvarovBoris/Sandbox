package com.uvarov.sandbox.ui.breeds

import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.api.BreedsResponse
import com.uvarov.sandbox.api.DogService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class BreedsViewModel constructor(private val dogService: DogService) : ViewModel() {

    fun requestBreeds() {
        dogService.getBreeds().enqueue(object : Callback<BreedsResponse> {
            override fun onResponse(call: Call<BreedsResponse>, response: Response<BreedsResponse>) {
                Timber.e(response.toString())
            }

            override fun onFailure(call: Call<BreedsResponse>, t: Throwable) {
                Timber.e(t)
            }
        })
    }
}
