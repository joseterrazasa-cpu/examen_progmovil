package org.ucb.appp1.starwars.presentation.viewmodel

data class StarWarsState(
    val characterId: String = "1",
    val isLoading: Boolean = false,
    val error: String? = null,
    val name: String? = null,
    val height: String? = null,
    val mass: String? = null,
    val hairColor: String? = null,
    val skinColor: String? = null,
    val eyeColor: String? = null,
    val gender: String? = null,
)
