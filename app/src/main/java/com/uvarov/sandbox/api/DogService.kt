package com.uvarov.sandbox.api

import retrofit2.Call
import retrofit2.http.GET

interface DogService {

    @GET("breeds/list/all")
    fun getBreeds(): Call<BreedsResponse>
}