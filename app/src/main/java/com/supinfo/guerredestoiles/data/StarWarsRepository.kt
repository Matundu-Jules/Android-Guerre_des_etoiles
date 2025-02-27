package com.supinfo.guerredestoiles.data

import com.supinfo.guerredestoiles.models.Character
import com.supinfo.guerredestoiles.models.CharacterResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class StarWarsRepository {

    private val client = HttpClient(CIO){
        install(ContentNegotiation) {
            json(Json{ ignoreUnknownKeys = true })
        }
    }

    suspend fun getStarWarsCharacters(): List<Character> {
        val response: HttpResponse = client.get("https://swapi.dev/api/people/")
        val result: CharacterResponse = response.body()
        return result.results
    }
}