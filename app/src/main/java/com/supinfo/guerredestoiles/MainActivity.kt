package com.supinfo.guerredestoiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.supinfo.guerredestoiles.components.StarWarsApp
import com.supinfo.guerredestoiles.data.StarWarsRepository

class MainActivity : ComponentActivity() {
    private val repository = StarWarsRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StarWarsApp(repository)
        }
    }
}
