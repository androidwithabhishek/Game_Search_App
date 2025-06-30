package abhishek.gupta.search.domain.di

import abhishek.gupta.search.domain.useCase.SearchGamesUseCase

import org.koin.dsl.module

fun getSearchDomainModule() = module {
    factory { SearchGamesUseCase(searchRepository = get()) }
}