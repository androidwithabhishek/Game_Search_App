package abhishekg.gupta.favorite.domain.useCase

import abhishekg.gupta.favorite.domain.repository.FavoriteRepository

class GetAllLocalCachedGamesUseCase(private val favoriteRepository: FavoriteRepository) {



    operator fun invoke() = favoriteRepository.getAllGames()

}