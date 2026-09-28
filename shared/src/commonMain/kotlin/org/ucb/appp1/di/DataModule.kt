package org.ucb.appp1.di

import org.koin.dsl.module
import org.ucb.appp1.starwars.data.datasource.StarWarsRemoteDataSource
import org.ucb.appp1.starwars.data.repository.StarWarsRepositoryImpl
import org.ucb.appp1.starwars.data.service.StarWarsApiService
import org.ucb.appp1.starwars.domain.repository.StarWarsRepository
import org.ucb.appp1.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.appp1.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.appp1.userinformation.data.service.GitHubApiService
import org.ucb.appp1.userinformation.domain.repository.GithubRepository

val dataModule = module {
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository>{GithubRepositoryImpl(get())}

    single<StarWarsRemoteDataSource> { StarWarsApiService() }
    single<StarWarsRepository> { StarWarsRepositoryImpl(get()) }
}
