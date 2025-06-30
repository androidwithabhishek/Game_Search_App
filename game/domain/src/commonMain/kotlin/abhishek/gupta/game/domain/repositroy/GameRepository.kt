package abhishek.gupta.game.domain.repositroy

import abhishek.gupta.common.domain.model.Game
import abhishek.gupta.game.domain.model.GameDetails


interface GameRepository {

    suspend fun getGames():Result<List<Game>>

    suspend fun  getDetails(id:Int):Result<GameDetails>


    suspend fun save(id: Int,image: String,name: String)

    suspend fun delete(id: Int)


}