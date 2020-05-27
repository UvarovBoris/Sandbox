package com.uvarov.sandbox.ui.breed.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uvarov.sandbox.api.BreedImagesResponse
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.model.BreedImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class BreedDetailViewModel constructor(private val dogService: DogService) : ViewModel() {

    val breedImagesLD: MutableLiveData<List<BreedImage>> = MutableLiveData()

    fun requestBreedImages(breed: String) {
        dogService.getBreedImages(breed).enqueue(object : Callback<BreedImagesResponse> {
            override fun onResponse(call: Call<BreedImagesResponse>, response: Response<BreedImagesResponse>) {
                breedImagesLD.value = response.body()?.breedImages
            }

            override fun onFailure(call: Call<BreedImagesResponse>, t: Throwable) {
                Timber.e(t)
            }
        })
    }
}
