package com.etf.testing.workshop.domain

import com.etf.testing.workshop.domain.model.Breed
import com.etf.testing.workshop.domain.model.Dog

interface DogsRepository {
    suspend fun getBreedsList(): Result<List<Breed>>
    suspend fun getBreedDetails(breed: Breed): Result<Dog>
}
