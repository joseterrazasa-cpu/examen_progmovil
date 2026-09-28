package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.starwars.domain.usecase.FindCharacterUseCase
import org.ucb.appp1.userinformation.domain.usecase.FindAliasUseCase

val domainModule = module {
    singleOf(::FindAliasUseCase)
    singleOf(::FindCharacterUseCase)
}

