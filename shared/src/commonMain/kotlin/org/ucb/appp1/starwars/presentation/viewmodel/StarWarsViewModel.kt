package org.ucb.appp1.starwars.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.appp1.starwars.domain.usecase.FindCharacterUseCase

class StarWarsViewModel(
    val findCharacterUseCase: FindCharacterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<StarWarsState>(StarWarsState())
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<StarWarsEffect>()
    val effect = _effect.asSharedFlow()

    fun emitEvent(event: StarWarsEvent) {
        when (event) {
            is StarWarsEvent.OnCharacterIdChange -> {
                _state.update { it.copy(characterId = event.value) }
            }
            StarWarsEvent.OnBack -> {
                emitEffect(StarWarsEffect.NavigateToBack)
            }
            StarWarsEvent.OnSubmit -> {
                viewModelScope.launch {
                    _state.update { it.copy(isLoading = true, error = null) }
                    findCharacterUseCase.invoke(_state.value.characterId).fold(
                        onSuccess = { character ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    name = character.name,
                                    height = character.height,
                                    mass = character.mass,
                                    hairColor = character.hairColor,
                                    skinColor = character.skinColor,
                                    eyeColor = character.eyeColor,
                                    gender = character.gender
                                )
                            }
                        },
                        onFailure = { throwable ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    error = throwable.message ?: "Error al buscar personaje"
                                )
                            }
                        }
                    )
                }
            }
        }
    }

    private fun emitEffect(effect: StarWarsEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }
}
