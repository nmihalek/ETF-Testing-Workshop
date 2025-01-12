package com.etf.testing.workshop.presentation.dogs.model

import com.etf.testing.workshop.domain.model.Breed
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class BreedUi(
    val name: String,
    val subBreed: String = ""
) {
    companion object {
        fun fromDomain(breed: Breed): BreedUi {
            return BreedUi(
                name = breed.name,
                subBreed = breed.subBreed
            )
        }
    }

    fun getFullBreedName(): String {
        return if (subBreed.isEmpty()) {
            name.capitalize()
        } else {
            "${subBreed.capitalize()} ${name.capitalize()}"
        }
    }

    fun serialize(): String = Json.encodeToString(Breed(name, subBreed))
}

fun Breed.toUi(): BreedUi {
    return BreedUi.fromDomain(this)
}
