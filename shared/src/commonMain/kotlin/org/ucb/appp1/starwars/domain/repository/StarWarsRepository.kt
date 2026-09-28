package org.ucb.appp1.starwars.domain.repository

import org.ucb.appp1.starwars.domain.model.StarWarsCharacterModel

interface StarWarsRepository {
    suspend fun findCharacterById(id: String): Result<StarWarsCharacterModel>
}
