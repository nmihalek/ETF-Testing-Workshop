package com.etf.testing.workshop.data.dogs

import com.etf.testing.workshop.data.dogs.model.BreedImagesResponse
import com.etf.testing.workshop.data.dogs.model.BreedsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DogCeoService {

    @GET("breeds/list/all")
    suspend fun getAllBreeds(): BreedsResponse

    @GET("breed/{breed}/images")
    suspend fun getImagesByBreed(@Path("breed") breed: String): BreedImagesResponse
}