package org.ucb.appp1.starwars.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.appp1.starwars.presentation.viewmodel.StarWarsEffect
import org.ucb.appp1.starwars.presentation.viewmodel.StarWarsEvent
import org.ucb.appp1.starwars.presentation.viewmodel.StarWarsViewModel

@Composable
fun StarWarsScreen(viewModel: StarWarsViewModel = koinViewModel()) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is StarWarsEffect.ShowToast -> {
                    // Todo
                }
                StarWarsEffect.NavigateToBack -> {
                    // Todo
                }
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = state.value.characterId,
            onValueChange = {
                viewModel.emitEvent(StarWarsEvent.OnCharacterIdChange(it))
            },
            label = { Text("ID del Personaje") }
        )
        Button(onClick = {
            viewModel.emitEvent(StarWarsEvent.OnSubmit)
        }) {
            Text("Buscar")
        }

        if (state.value.isLoading) {
            CircularProgressIndicator()
        }

        state.value.error?.let {
            Text("Error: $it")
        }

        state.value.name?.let {
            Text("Nombre: $it")
        }
        state.value.height?.let {
            Text("Altura: $it")
        }
        state.value.mass?.let {
            Text("Peso: $it")
        }
        state.value.hairColor?.let {
            Text("Color de cabello: $it")
        }
        state.value.skinColor?.let {
            Text("Color de piel: $it")
        }
        state.value.eyeColor?.let {
            Text("Color de ojos: $it")
        }
        state.value.gender?.let {
            Text("Género: $it")
        }
    }
}
