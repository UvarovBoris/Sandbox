package com.uvarov.sandbox.ui.breed.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvarov.sandbox.api.BreedImagesResponse
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.model.BreedImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class BreedDetailViewModel constructor(private val dogService: DogService) : ViewModel() {

    val breedImagesLD: MutableLiveData<List<BreedImage>> = MutableLiveData()

    fun getBreedImages(breed: String) {
        viewModelScope.launch {
            try {
                val response: Response<BreedImagesResponse> = requestBreedImagesFromNetwork(breed)
                breedImagesLD.value = response.body()?.breedImages
            } catch (e: IOException) {
                Timber.e(e)
            }
        }
    }

    private suspend fun requestBreedImagesFromNetwork(breed: String): Response<BreedImagesResponse> = withContext(Dispatchers.IO) {
        dogService.getBreedImages(breed).execute()
    }
}