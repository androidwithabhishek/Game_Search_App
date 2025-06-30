package abhishek.gupta.game.domain.useCases

import abhishek.gupta.game.domain.repositroy.GameRepository

class SaveGameUseCase(private val gameRepository: GameRepository) {

    suspend operator fun invoke(id: Int, image: String, name: String){
        gameRepository.save  (id = id,image = image,name= name)
    }



}