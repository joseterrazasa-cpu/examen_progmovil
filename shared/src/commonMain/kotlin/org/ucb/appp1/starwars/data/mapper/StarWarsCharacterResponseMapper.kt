package org.ucb.appp1.starwars.data.mapper

import org.ucb.appp1.starwars.data.dto.StarWarsCharacterDto
import org.ucb.appp1.starwars.domain.model.StarWarsCharacterModel

fun StarWarsCharacterDto.toDomain(): StarWarsCharacterModel = StarWarsCharacterModel(
    name = name ?: "",
    height = height ?: "",
    mass = mass ?: "",
    hairColor = hairColor ?: "",
    skinColor = skinColor ?: "",
    eyeColor = eyeColor ?: "",
    gender = gender ?: ""
)
