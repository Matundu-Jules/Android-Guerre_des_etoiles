package com.supinfo.guerredestoiles.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.supinfo.guerredestoiles.data.StarWarsRepository
import kotlinx.coroutines.launch
import com.supinfo.guerredestoiles.models.Character

@Composable
fun StarWarsApp(repository: StarWarsRepository) {
    var characters by remember { mutableStateOf<List<Character>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                characters = repository.getStarWarsCharacters()
            } catch (e: Exception) {
                Log.e("StarWarsApp", "Erreur de récupération des personnages", e)
            }
        }
    }

    StarWarsScreen(characters)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StarWarsScreen(characters: List<Character>) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Star Wars Characters",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                        },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),

            )
        }
    ){ paddingValues ->
        LazyColumn (
            contentPadding = paddingValues,
            modifier = Modifier.padding(16.dp)
        ) {
            items(characters) { character ->
                CharacterItem(character)
            }
        }
    }
}

@Composable
fun CharacterItem(character: Character) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column (modifier = Modifier.padding(16.dp)) {
            Text(text = character.name, style = MaterialTheme.typography.titleLarge)
            Text(text = "Taille : ${character.height} cm", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Poids : ${character.mass} kg", style = MaterialTheme.typography.bodyMedium)
        }
    }
}