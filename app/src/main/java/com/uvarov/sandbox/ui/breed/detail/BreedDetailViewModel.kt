package com.uvarov.sandbox.ui.breed.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvarov.sandbox.api.BreedImagesResponse
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.model.BreedImage
import com.uvarov.sandbox.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class BreedDetailViewModel constructor(private val dogService: DogService) : ViewModel() {

    private val _breedImagesLD: MutableLiveData<List<BreedImage>> = MutableLiveData()
    val breedImagesLD: LiveData<List<BreedImage>> get() = _breedImagesLD
    private val _breedImagesErrorLD: MutableLiveData<SingleEvent<Throwable>> = MutableLiveData()
    val breedImagesErrorLD: LiveData<SingleEvent<Throwable>> get() = _breedImagesErrorLD

    fun getBreedImages(breed: String) {
        viewModelScope.launch {
            try {
                val response: Response<BreedImagesResponse> = requestBreedImagesFromNetwork(breed)
                _breedImagesLD.value = response.body()?.breedImages
            } catch (e: IOException) {
                Timber.e(e)
                _breedImagesErrorLD.value = SingleEvent(e)
            }
        }
    }

    private suspend fun requestBreedImagesFromNetwork(breed: String): Response<BreedImagesResponse> = withContext(Dispatchers.IO) {
        dogService.getBreedImages(breed).execute()
    }
}