package org.ucb.appp1.starwars.data.service

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.ucb.appp1.starwars.data.datasource.StarWarsRemoteDataSource
import org.ucb.appp1.starwars.data.dto.StarWarsCharacterDto

class StarWarsApiService : StarWarsRemoteDataSource {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    override suspend fun getCharacter(characterId: String): StarWarsCharacterDto {
        val response = client.get("https://swapi.dev/api/people/$characterId/")
        try {
            return response.body<StarWarsCharacterDto>()
        } catch (e: Exception) {
            throw e
        }
    }
}
