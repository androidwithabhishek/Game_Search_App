package abhishek.gupta.game.ui.di


import abhishek.gupta.game.ui.game.GameViewModel
import abhishek.gupta.game.ui.gameDetails.GameDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun getGameUIModule() = module {

    viewModel {
        GameViewModel(
            getGameUseCases = get()
        )
    }
    viewModel {
        GameDetailsViewModel(
            getGameDetailsUseCase = get(),
            deleteGameUseCase = get(),
            saveGameUseCase = get(),
        )
    }

}