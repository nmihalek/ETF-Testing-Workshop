package com.etf.testing.workshop.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Breed(
    val name: String,
    val subBreed: String = ""
)
