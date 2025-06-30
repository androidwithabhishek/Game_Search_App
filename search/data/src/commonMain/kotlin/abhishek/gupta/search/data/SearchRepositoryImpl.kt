package abhishek.gupta.search.data

import abhishek.gupta.common.domain.model.Game
import abhishek.gupta.common.mapper.toDomainListOfGame
import abhishek.gupta.coreNetwork.apiService.ApiService
import abhishek.gupta.search.domain.repositroy.SearchRepository

class SearchRepositoryImpl(
    private val apiService: ApiService
) : SearchRepository {

    override suspend fun search(q: String): Result<List<Game>> {
        return try {
            val response = apiService.search(q)
            val data = response.getOrThrow().results.toDomainListOfGame()
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}