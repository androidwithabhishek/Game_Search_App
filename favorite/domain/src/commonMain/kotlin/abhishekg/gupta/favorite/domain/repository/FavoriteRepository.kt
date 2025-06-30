package abhishekg.gupta.favorite.domain.repository

import abhishek.gupta.common.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {


    fun getAllGames(): Flow<List<Game>>

    suspend fun upsert(id: Int,image: String,name: String,)

    suspend fun delete(id: Int)


}