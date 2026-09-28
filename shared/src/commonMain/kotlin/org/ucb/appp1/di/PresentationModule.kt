package org.ucb.appp1.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.appp1.starwars.presentation.viewmodel.StarWarsViewModel
import org.ucb.appp1.userinformation.presentation.viewmodel.UserInformationViewModel

val presentationModule = module {
    viewModelOf(::UserInformationViewModel)
    viewModelOf(::StarWarsViewModel)
}

