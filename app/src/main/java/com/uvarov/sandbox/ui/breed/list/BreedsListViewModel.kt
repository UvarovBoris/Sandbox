package com.uvarov.sandbox.ui.breed.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvarov.sandbox.api.BreedsResponse
import com.uvarov.sandbox.api.DogService
import com.uvarov.sandbox.model.Breed
import com.uvarov.sandbox.utils.SingleEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

class BreedsListViewModel constructor(private val dogService: DogService) : ViewModel() {

    private val _breedsLD: MutableLiveData<List<Breed>> = MutableLiveData()
    val breedsLD: LiveData<List<Breed>> get() = _breedsLD
    private val _breedsErrorLD: MutableLiveData<SingleEvent<Throwable>> = MutableLiveData()
    val breedsErrorLD: LiveData<SingleEvent<Throwable>> get() = _breedsErrorLD

    fun getBreeds() {
        viewModelScope.launch {
            try {
                val response: Response<BreedsResponse> = requestBreedsFromNetwork()
                _breedsLD.value = response.body()?.breeds
            } catch (e: IOException) {
                Timber.e(e)
                _breedsErrorLD.value = SingleEvent(e)
            }
        }
    }

    private suspend fun requestBreedsFromNetwork(): Response<BreedsResponse> = withContext(Dispatchers.IO) {
        dogService.getBreeds().execute()
    }
}