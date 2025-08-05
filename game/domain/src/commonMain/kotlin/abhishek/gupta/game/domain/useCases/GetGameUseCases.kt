package abhishek.gupta.game.domain.useCases


import abhishek.gupta.common.domain.model.Game
import abhishek.gupta.game.domain.repositroy.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetGameUseCases(private val gameRepository: GameRepository) {

    operator fun invoke(page: Int) = flow<Result<List<Game>>> {
        emit(gameRepository.getGames(page))

    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.IO)

}

