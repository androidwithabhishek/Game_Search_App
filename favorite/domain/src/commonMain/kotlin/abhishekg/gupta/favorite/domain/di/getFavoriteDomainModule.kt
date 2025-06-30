package abhishekg.gupta.favorite.domain.di

import abhishekg.gupta.favorite.domain.useCase.DeleteUseCase
import abhishekg.gupta.favorite.domain.useCase.GetAllLocalCachedGamesUseCase
import abhishekg.gupta.favorite.domain.useCase.UpsertUseCase
import org.koin.core.module.Module


import org.koin.dsl.module

fun getFavoriteDomainModule(): Module {

    return module {
        factory { DeleteUseCase(get()) }
        factory { GetAllLocalCachedGamesUseCase(get()) }
        factory { UpsertUseCase(get()) }
    }

}