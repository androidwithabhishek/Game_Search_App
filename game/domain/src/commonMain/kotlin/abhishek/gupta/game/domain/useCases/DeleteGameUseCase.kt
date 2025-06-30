package abhishek.gupta.game.domain.useCases

import abhishek.gupta.game.domain.repositroy.GameRepository

class DeleteGameUseCase(private val gameRepository: GameRepository) {


    suspend operator fun invoke(id: Int) {

        gameRepository.delete(id = id)

    }
}