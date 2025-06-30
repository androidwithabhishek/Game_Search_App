package abhishek.gupta.game.domain.di

import abhishek.gupta.game.domain.useCases.DeleteGameUseCase
import abhishek.gupta.game.domain.useCases.GetGameDetailsUseCase
import abhishek.gupta.game.domain.useCases.GetGameUseCases
import abhishek.gupta.game.domain.useCases.SaveGameUseCase
import org.koin.dsl.module

fun getGameDomainModel() = module {

    factory { GetGameUseCases(gameRepository = get()) }
    factory { GetGameDetailsUseCase(gameRepository = get()) }
    factory { DeleteGameUseCase(gameRepository = get()) }
    factory { SaveGameUseCase(gameRepository = get()) }










}