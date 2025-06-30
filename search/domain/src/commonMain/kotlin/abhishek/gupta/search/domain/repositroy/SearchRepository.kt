package abhishek.gupta.search.domain.repositroy

import abhishek.gupta.common.domain.model.Game

interface SearchRepository {

  suspend fun search(q: String): Result<List<Game>>

}