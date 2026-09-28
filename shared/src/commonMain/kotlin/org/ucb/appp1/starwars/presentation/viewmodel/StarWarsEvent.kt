package org.ucb.appp1.starwars.presentation.viewmodel

sealed interface StarWarsEvent {
    object OnBack : StarWarsEvent
    object OnSubmit : StarWarsEvent
    data class OnCharacterIdChange(val value: String) : StarWarsEvent
}
