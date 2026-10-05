package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.appp1.exchangerate.domain.usecase.AddCustomRateUseCase
import org.ucb.appp1.exchangerate.domain.usecase.DeleteSavedRateUseCase
import org.ucb.appp1.exchangerate.domain.usecase.GetAvailableRatesUseCase
import org.ucb.appp1.exchangerate.domain.usecase.GetSavedRatesUseCase
import org.ucb.appp1.exchangerate.domain.usecase.SaveExchangeRateUseCase
import org.ucb.appp1.signin.domain.usecase.AuthenticateUseCase
import org.ucb.appp1.userinformation.domain.usecase.FindAliasUseCase

val domainModule = module {
    singleOf(::AuthenticateUseCase)
    singleOf(::FindAliasUseCase)

    singleOf(::GetAvailableRatesUseCase)
    singleOf(::GetSavedRatesUseCase)
    singleOf(::SaveExchangeRateUseCase)
    singleOf(::AddCustomRateUseCase)
    singleOf(::DeleteSavedRateUseCase)
}
