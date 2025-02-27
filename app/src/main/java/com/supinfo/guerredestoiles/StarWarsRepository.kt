package com.supinfo.guerredestoiles

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
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

@Serializable
data class CharacterResponse(
    val count: Int,  // Ajout du champ count
    val next: String?, // Ajout du champ next (URL de la page suivante)
    val results: List<Character>
)

@Serializable
data class Character(
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val created: String,
    val edited: String,
    val url: String
)