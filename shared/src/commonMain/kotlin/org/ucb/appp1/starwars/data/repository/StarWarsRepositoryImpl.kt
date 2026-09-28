package org.ucb.appp1.starwars.data.repository

import org.ucb.appp1.starwars.data.datasource.StarWarsRemoteDataSource
import org.ucb.appp1.starwars.data.mapper.toDomain
import org.ucb.appp1.starwars.domain.model.StarWarsCharacterModel
import org.ucb.appp1.starwars.domain.repository.StarWarsRepository

class StarWarsRepositoryImpl(val dataSource: StarWarsRemoteDataSource) : StarWarsRepository {
    override suspend fun findCharacterById(id: String): Result<StarWarsCharacterModel> {
        return try {
            Result.success(dataSource.getCharacter(id).toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
