package com.uvarov.sandbox.ui.breed.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvarov.sandbox.api.BreedsResponse
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.model.Breed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class BreedsListViewModel constructor(private val dogService: DogService) : ViewModel() {

    val breedsLD: MutableLiveData<List<Breed>> = MutableLiveData()

    fun getBreeds() {
        viewModelScope.launch {
            try {
                val response: Response<BreedsResponse> = requestBreedsFromNetwork()
                breedsLD.value = response.body()?.breeds
            } catch (e: IOException) {
                Timber.e(e)
            }
        }
    }

    private suspend fun requestBreedsFromNetwork(): Response<BreedsResponse> = withContext(Dispatchers.IO) {
        dogService.getBreeds().execute()
    }
}