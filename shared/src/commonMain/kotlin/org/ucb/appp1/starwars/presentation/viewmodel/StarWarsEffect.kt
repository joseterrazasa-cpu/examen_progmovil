package org.ucb.appp1.starwars.presentation.viewmodel

sealed interface StarWarsEffect {
    data class ShowToast(val message: String) : StarWarsEffect
    object NavigateToBack : StarWarsEffect
}
