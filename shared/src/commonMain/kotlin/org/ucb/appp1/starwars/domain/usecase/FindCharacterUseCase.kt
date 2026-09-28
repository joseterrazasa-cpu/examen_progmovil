package org.ucb.appp1.starwars.domain.usecase

import org.ucb.appp1.starwars.domain.model.StarWarsCharacterModel
import org.ucb.appp1.starwars.domain.repository.StarWarsRepository

class FindCharacterUseCase(
    val repository: StarWarsRepository
) {
    suspend fun invoke(id: String): Result<StarWarsCharacterModel> {
        return repository.findCharacterById(id)
    }
}
