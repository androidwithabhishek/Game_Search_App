package abhishek.gupta.app.di


import abhishek.gupta.coreDatabase.di.getCoreDatabaseModule
import abhishek.gupta.coreNetwork.di.getCoreNetworkModule
import abhishek.gupta.favorite.ui.di.getFavoriteUiModule
import abhishek.gupta.game.data.di.getGameDataModule
import abhishek.gupta.game.ui.di.getGameUIModule
import abhishek.gupta.game.domain.di.getGameDomainModel

import abhishek.gupta.search.data.di.getSearchDataModule
import abhishek.gupta.search.domain.di.getSearchDomainModule

import abhishek.gupta.search.ui.di.getSearchUiModule
import abhishekg.gupta.favorite.data.di.getFavoriteDataModule
import abhishekg.gupta.favorite.domain.di.getFavoriteDomainModule
import org.koin.core.KoinApplication

import org.koin.core.context.startKoin


fun initKoin(koinApplication: ((KoinApplication) -> Unit)? = null) {
    startKoin {
        koinApplication?.invoke(this)
        modules(
            getCoreNetworkModule(),
            getGameDomainModel(),
            getGameDataModule(),
            getGameUIModule(),
            getSearchDataModule(),
            getSearchDomainModule(),
            getSearchUiModule(),
            getCoreDatabaseModule(),
            getFavoriteDataModule(),
            getFavoriteDomainModule(),
            getFavoriteUiModule()








        )
    }
}