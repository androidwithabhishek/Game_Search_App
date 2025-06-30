package abhishekg.gupta.favorite.domain.useCase

import abhishekg.gupta.favorite.domain.repository.FavoriteRepository

class UpsertUseCase(private val favoriteRepository: FavoriteRepository) {

  suspend   operator fun invoke (id:Int,image: String,name: String){
        favoriteRepository.upsert(id =id,image=image,name = name )
    }
}