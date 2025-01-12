package com.etf.testing.workshop.data.dogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BreedImagesResponse(
    @SerialName("message")
    val images: List<String>,
    val status: String
)
