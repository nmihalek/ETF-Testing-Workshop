package com.etf.testing.workshop.presentation.dogs.model

import com.etf.testing.workshop.domain.model.Dog

data class DogUi(
    val breed: BreedUi,
    val images: List<String>
) {
    companion object {
        fun fromDomain(dog: Dog): DogUi {
            return DogUi(
                breed = dog.breed.toUi(),
                images = dog.images
            )
        }
    }
}

fun Dog.toUi(): DogUi = DogUi.fromDomain(this)
