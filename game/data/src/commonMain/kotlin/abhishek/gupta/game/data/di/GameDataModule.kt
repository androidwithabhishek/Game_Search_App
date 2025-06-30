package abhishek.gupta.game.data.di

import abhishek.gupta.coreDatabase.AppDatabase
import abhishek.gupta.game.data.GameRepositoryImpl
import abhishek.gupta.game.domain.repositroy.GameRepository
import org.koin.dsl.module


fun getGameDataModule() = module {
    factory<GameRepository> {
        GameRepositoryImpl(apiService = get(), appDatabase = get<AppDatabase>())
    }


}