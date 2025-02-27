package com.supinfo.guerredestoiles.models

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    val count: Int,  // Ajout du champ count
    val next: String?, // Ajout du champ next (URL de la page suivante)
    val results: List<Character>
)