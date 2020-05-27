package com.uvarov.sandbox.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogService {

    @GET("breeds/list/all")
    fun getBreeds(): Call<BreedsResponse>

    @GET("breed/{breed}/images")
    fun getBreedImages(@Path("breed") breed: String): Call<BreedImagesResponse>

}