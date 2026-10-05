package org.ucb.appp1.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.appp1.exchangerate.presentation.viewmodel.ExchangeRateViewModel
import org.ucb.appp1.movies.presentation.viewmodel.DollarViewModel
import org.ucb.appp1.signin.presentation.viewmodel.LoginViewModel
import org.ucb.appp1.userinformation.presentation.viewmodel.UserInformationViewModel

val presentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::UserInformationViewModel)
    viewModelOf(::DollarViewModel)
    viewModelOf(::ExchangeRateViewModel)
}
