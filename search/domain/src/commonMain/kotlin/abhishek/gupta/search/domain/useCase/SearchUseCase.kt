package abhishek.gupta.search.domain.useCase

import abhishek.gupta.search.domain.repositroy.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchGamesUseCase(private val searchRepository: SearchRepository) {

    operator fun invoke(q: String) = flow {
        emit(searchRepository.search(q))
    }.catch { error ->
        emit(Result.failure(error))
    }.flowOn(Dispatchers.IO)

}