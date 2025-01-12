package com.etf.testing.workshop.data.dogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BreedsResponse(
    @SerialName("message")
    val breeds: Map<String, List<String>>,
    val status: String
)
