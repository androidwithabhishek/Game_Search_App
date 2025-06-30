package abhishek.gupta.common.mapper



import abhishek.gupta.common.domain.model.Game
import abhishek.gupta.coreNetwork.model.game.Result


fun List<Result>.toDomainListOfGame(): List<Game> = map{
    Game(
        id = it.id,
        name = it.name,
        imageUrl = it.background_image
    )
}