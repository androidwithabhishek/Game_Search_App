package abhishek.gupta.search.data.di

import abhishek.gupta.search.data.SearchRepositoryImpl
import abhishek.gupta.search.domain.repositroy.SearchRepository
import org.koin.dsl.module

fun getSearchDataModule() = module {
    factory<SearchRepository> { SearchRepositoryImpl(apiService = get()) }
}



