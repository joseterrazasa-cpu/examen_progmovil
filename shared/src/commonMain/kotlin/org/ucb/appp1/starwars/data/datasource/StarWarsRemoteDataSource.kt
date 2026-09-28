package org.ucb.appp1.starwars.data.datasource

import org.ucb.appp1.starwars.data.dto.StarWarsCharacterDto

interface StarWarsRemoteDataSource {
    suspend fun getCharacter(characterId: String): StarWarsCharacterDto
}
