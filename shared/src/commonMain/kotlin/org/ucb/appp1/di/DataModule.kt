package org.ucb.appp1.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.ucb.appp1.config.AppDatabase
import org.ucb.appp1.exchangerate.data.dao.ExchangeRateDao
import org.ucb.appp1.exchangerate.data.datasource.ExchangeRateLocalDataSource
import org.ucb.appp1.exchangerate.data.datasource.ExchangeRateRemoteDataSource
import org.ucb.appp1.exchangerate.data.repository.ExchangeRateRepositoryImpl
import org.ucb.appp1.exchangerate.domain.repository.ExchangeRateRepository
import org.ucb.appp1.movies.data.dao.DollarDao
import org.ucb.appp1.movies.data.datasource.DollarLocalDataSource
import org.ucb.appp1.movies.data.repository.DollarRepositoryImpl
import org.ucb.appp1.movies.domain.repository.DollarRepository
import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.data.service.GitHubApiService
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

val dataModule = module {
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository> { GithubRepositoryImpl(get()) }

    single<DollarDao> { get<AppDatabase>().getDao() }
    singleOf(::DollarLocalDataSource)
    single<DollarRepository> { DollarRepositoryImpl(get()) }

    single<ExchangeRateDao> { get<AppDatabase>().exchangeRateDao() }
    singleOf(::ExchangeRateLocalDataSource)
    singleOf(::ExchangeRateRemoteDataSource)
    singleOf(::ExchangeRateRepositoryImpl) bind ExchangeRateRepository::class
}
