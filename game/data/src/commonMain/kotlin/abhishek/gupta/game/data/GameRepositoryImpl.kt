package abhishek.gupta.game.data

import abhishek.gupta.common.domain.model.Game
import abhishek.gupta.common.mapper.toDomainListOfGame
import abhishek.gupta.coreDatabase.AppDatabase
import abhishek.gupta.coreDatabase.AppDatabaseQueries
import abhishek.gupta.coreNetwork.apiService.ApiService
import abhishek.gupta.game.data.mappers.toDomainGameDetails
import abhishek.gupta.game.domain.model.GameDetails

import abhishek.gupta.game.domain.repositroy.GameRepository

class GameRepositoryImpl(val apiService: ApiService, private val appDatabase: AppDatabase) :
    GameRepository {

    private val pageSize = 20
    override suspend fun getGames(page: Int): Result<List<Game>> {

        val result = apiService.getGames(page = page, pageSize = pageSize)


        return  if (result.isSuccess) {

           Result.success(result.getOrThrow().results.toDomainListOfGame())

        } else {
           Result.failure(result.exceptionOrNull()!!)
        }
    }
    fun resetPaging() {

    }
    override suspend fun getDetails(id: Int): Result<GameDetails> {

        val result = apiService.getDetail(id)
        return if (result.isSuccess) {

            Result.success(result.getOrThrow().toDomainGameDetails())
        } else {
            Result.failure(result.exceptionOrNull()!!)
        }
    }


    override suspend fun save(id: Int, image: String, name: String) {
        appDatabase.appDatabaseQueries.upsert(
            id = id.toLong(),
            image = image,
            name = name
        )
    }

    override suspend fun delete(id: Int) {
        appDatabase.appDatabaseQueries.delete(id = id.toLong())
    }
}


