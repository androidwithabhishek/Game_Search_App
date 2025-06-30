package abhishek.gupta.search.ui.di

import abhishek.gupta.search.ui.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


fun getSearchUiModule() = module {
    viewModel { SearchViewModel(searchGameUseCase = get()) }
}