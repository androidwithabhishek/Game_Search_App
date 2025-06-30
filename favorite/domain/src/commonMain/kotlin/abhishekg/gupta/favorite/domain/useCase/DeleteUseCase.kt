package abhishekg.gupta.favorite.domain.useCase

import abhishekg.gupta.favorite.domain.repository.FavoriteRepository


class DeleteUseCase(private val favoriteRepository: FavoriteRepository) {

    suspend operator fun invoke(id: Int){
        favoriteRepository.delete(id = id)
    }

}