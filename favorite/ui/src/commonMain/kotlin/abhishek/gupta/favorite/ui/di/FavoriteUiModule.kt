package abhishek.gupta.favorite.ui.di


import abhishek.gupta.favorite.ui.FavoriteViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


fun getFavoriteUiModule(): Module {
    return module {
        viewModel {
            FavoriteViewModel(
                getAllLocalCachedGameUseCase = get(),
                deleteUseCase = get()
            )
        }
    }

}

