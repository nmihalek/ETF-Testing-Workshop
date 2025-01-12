package com.etf.testing.workshop.data.dogs

import com.etf.testing.workshop.domain.DogsRepository
import com.etf.testing.workshop.domain.model.Breed
import com.etf.testing.workshop.domain.model.Dog

class DogCeoDogsRepository(
    private val dogCeoService: DogCeoService
): DogsRepository {
    override suspend fun getBreedsList(): Result<List<Breed>> {
        return try {
            val result = dogCeoService.getAllBreeds().breeds
                .map { item ->
                    if (item.value.isEmpty()) {
                        listOf(Breed(item.key))
                    } else {
                        item.value.map { subBreed -> Breed(item.key, subBreed) }
                    }
                }.flatten()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getBreedDetails(breed: Breed): Result<Dog> {
        return try {
            val response = dogCeoService.getImagesByBreed(breed.name)
                .images
                .filter { image ->
                    image.contains(breed.subBreed)
                }
            Result.success(Dog(breed, response))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
