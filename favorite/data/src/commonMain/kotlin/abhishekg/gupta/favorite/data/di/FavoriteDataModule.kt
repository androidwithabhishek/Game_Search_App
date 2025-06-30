package abhishekg.gupta.favorite.data.di

import abhishek.gupta.coreDatabase.AppDatabase
import abhishekg.gupta.favorite.data.repository.FavoriteRepositoryImpl
import abhishekg.gupta.favorite.domain.repository.FavoriteRepository
import androidx.lifecycle.ViewModelProvider
import org.koin.core.module.Module
import org.koin.dsl.module

fun getFavoriteDataModule(): Module {
    return module {

        factory<FavoriteRepository> { FavoriteRepositoryImpl(get<AppDatabase>()) }

    }
}